import string
def DetectName(text):
    """
    This function is used to look for names in the text file. We are also making sure that 
    the first letters are in uppercase.
    """

    name_str = text[0].strip()
    name_lst = name_str.split()
    if name_lst[0][0].isupper() and name_lst[-1][0].isupper():
        return name_str
    else:
        raise RuntimeError('The first letter of recorded name should be in uppercase.')

def DetectEmail(text):
    """
    Here we are trying to read the email address from the text file according to the rules 
    given.
    """
    for line in text:
        if '@' in line:
            email = line.strip()
            break
    lastfour = "." + email.split('.')[-1]
    username = email.split('@')[0]
    institution = email.split('@')[1]
    numbers = '0123456789'
    lower = 'qwertyuiopasdfghjklzxcvbnm'

    if lastfour != '.edu' and lastfour != '.com':
        return []
    elif institution[0] not in lower:
        return []

    for i in username:
        if i in numbers:
            return []
        elif i == '.':
            return []
    for char in institution:
        if char in numbers:
            return []
    return email 
        
    
def DetectCourses(text):
    """
    Here we are tyring to look for courses according to the instruction. After converting the 
    information to strings, we are also getting rid of the punctuations such as colons.
    """

    for line in text:
        if 'Courses' in line:
            courseline = line
            break
    courses_lst = []
    coursesplited = courseline.split('Courses')[-1].split(',')
    for i in range(len(coursesplited)):
        exclude = set(string.punctuation) #here we are creating a set with all the punctutations
        coursesplited[i] = ''.join(ch for ch in coursesplited[i] if ch not in exclude) #here we get rid them all
        courses = coursesplited[i].lstrip().rstrip()
        courses_lst.append(courses)

    return courses_lst
        

def DetectProjects(text):
    """
    This where we detect the project from project to a series of dashed lines
    """
    copy_text = text.copy()
    index = 0
    for line in copy_text:
        if 'Projects' in line:
            projects_index = index
        if "---------" in line:
            dash_index =  index
        index += 1
    projects_list = copy_text[projects_index + 1 : dash_index]
    projects_list = [i.strip() for i in projects_list]

    return projects_list

def read_file():
    """
    This function serves to read files
    """
    f = open('resume.txt', 'r')
    f_content = f.readlines()
    f.close()
    return f_content

def write_file(basicinfo, projects, courses):
    """
    This function is where we write the html script and save it in a file
    """
    f = open('resume.html', 'w')
    f_temp = open("resume_template.html", "r")
    temp_lines = f_temp.readlines()
    temp_str = ""
    for line in temp_lines[0:-3]: #not reading the very bottom according to the instruction given
        temp_str += line

    final_text = temp_str + "<div id=\"page-wrap\">\n"  + basicinfo + projects + courses + "</div>\n<body>\n</html>"
    f.write(final_text)


def create_email_link(email_address):
    """
    Here we create a link for the email address by adding html tags to the input email.
    """

    alternated_email = email_address.replace("@","[aT]")
    email_text = '<a href="mailto:{}">{}</a>'.format(email_address,alternated_email)

    return email_text

def basicinfo_html(name,email):
    """
    Here we are trying to write the name and email information into html.
    """

    basic_str = ''
    basic_str += surround_block("h1",name) + "\n" + surround_block("p","Email: " + email)

    return "<div>\n"+ basic_str + "\n</div>"



def project_html(projects):
    """
    This is the function that helps us to write project into html.
    """
    text = '<h2>Projects</h2>\n<ul>\n'
    for i in range(0, len(projects)):
        text += '<li>' + projects[i] + '</li>\n'
    text += '</ul>'
    return ("<div>\n"+ text + "\n</div>")

def courses_html(courses):
    """
    Here we are trying to add courses to the final output in html.
    """
    course = ', '.join(courses)
    text = "<div>\n"+ surround_block("h3", "Courses")+ "\n" + surround_block("span", course) + "\n</div>"
    return text


def surround_block(tag, text):
    """
    This function surrounds the given text with the given HTMLtag and returns the string.
    """
    text = "<{}>{}</{}>".format(tag,text,tag)
    return text

def main():
    resume_lines = read_file()
    get_name = DetectName(resume_lines)
    get_email = DetectEmail(resume_lines)
    get_projects = DetectProjects(resume_lines)
    get_courses = DetectCourses(resume_lines)
    basicinfo_html_str = basicinfo_html(get_name, create_email_link(get_email))
    project_html_str = project_html(get_projects)
    courses_html_str = courses_html(get_courses)
    #This where we write it for the final output
    write_file(basicinfo_html_str, project_html_str, courses_html_str)



if __name__ == '__main__':
    main()
