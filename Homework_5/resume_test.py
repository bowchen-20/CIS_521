import unittest

from make_website import *

class MakeWebsite_Test(unittest.TestCase):

    def setUp(self):
        self.resume = 'resume.txt'
        self.test_resume = 'resume_test.txt'

    def test_DetectName(self):
        self.assertEqual(DetectName(["Bowen Chen"]),'Bowen Chen')
        self.assertRaises(RuntimeError, DetectName, ["bowen chen"])

    #
    def test_DetectEmail(self):
        self.assertEqual(DetectEmail([" bowchen@seas.upenn.edu"]),'bowchen@seas.upenn.edu')
        self.assertEqual(DetectEmail([" bowchen@34seas.upenn.edu"]), [])

    #
    def test_DetectCourses(self):
        self.assertEqual(DetectCourses(["Courses !#%^#$%$# aa, bb"]), ["aa", "bb"])
        self.assertEqual(DetectCourses(["Courses"]), [''])


    def testDetectProjects(self):
        projects = []
        self.assertEqual(DetectProjects(["Projects", "adfsa", "asf", "---------"]), ["adfsa", "asf"])
        self.assertEqual(DetectProjects(["Projects", "adfsa", "asf","-----------------------------"]), ["adfsa", "asf"])

    
    def test_basicinfo_html(self):
        self.assertEqual(basicinfo_html('Bowen Chen', '<a href="mailto:bowchen@seas.upenn.edu">bowchen[aT]seas.upenn.edu</a>'),'<div>\n<h1>Bowen Chen</h1>\n<p>Email: <a href="mailto:bowchen@seas.upenn.edu">bowchen[aT]seas.upenn.edu</a></p>\n</div>')

    def test_courses_html(self):
        self.assertEqual(courses_html(['Calc','Econ']),'<div>\n<h3>Courses</h3>\n<span>Calc, Econ</span>\n</div>')
    
    def test_project_html(self):
        self.assertEqual(project_html(['Make America great again']),"<div>\n"+ '<h2>Projects</h2>\n<ul>\n'+'<li>'+'Make America great again'+'</li>\n' +'</ul>'+ "\n</div>")

    
    def test_surround_block(self):

        # test surrounding html
        self.assertEqual(surround_block('h1', 'Eagles'), "<h1>Eagles</h1>")

        # test surrounding html
        self.assertEqual(surround_block('p', 'Lorem ipsum dolor sit amet, consectetur ' +
                                        'adipiscing elit. Sed ac felis sit amet ante porta ' +
                                        'hendrerit at at urna. Donec in vehicula ex. Aenean ' +
                                        'scelerisque accumsan augue, vitae cursus sapien venenatis ' +
                                        'ac. Quisque dui tellus, rutrum hendrerit nisl vitae, ' +
                                        'pretium mollis lorem. Pellentesque eget quam a justo ' +
                                        'egestas vehicula in eu justo. Nulla cursus, metus vitae ' +
                                        'tincidunt luctus, turpis lectus bibendum purus, eget ' +
                                        'consequat est lacus ac nibh. In interdum metus vel est ' +
                                        'posuere aliquet. Maecenas et euismod arcu, eu auctor ' +
                                        'libero. Phasellus lectus magna, auctor ac auctor in, ' +
                                        'suscipit id turpis. Maecenas dignissim enim ac justo ' +
                                        'tincidunt viverra. Sed interdum molestie tincidunt. Etiam ' +
                                        'vitae justo tincidunt, blandit augue id, volutpat ligula. ' +
                                        'Aenean ut aliquet mi. Suspendisse consequat blandit posuere.'),
                                        '<p>Lorem ipsum dolor sit amet, consectetur ' +
                                        'adipiscing elit. Sed ac felis sit amet ante porta ' +
                                        'hendrerit at at urna. Donec in vehicula ex. Aenean ' +
                                        'scelerisque accumsan augue, vitae cursus sapien venenatis ' +
                                        'ac. Quisque dui tellus, rutrum hendrerit nisl vitae, ' +
                                        'pretium mollis lorem. Pellentesque eget quam a justo ' +
                                        'egestas vehicula in eu justo. Nulla cursus, metus vitae ' +
                                        'tincidunt luctus, turpis lectus bibendum purus, eget ' +
                                        'consequat est lacus ac nibh. In interdum metus vel est ' +
                                        'posuere aliquet. Maecenas et euismod arcu, eu auctor ' +
                                        'libero. Phasellus lectus magna, auctor ac auctor in, ' +
                                        'suscipit id turpis. Maecenas dignissim enim ac justo ' +
                                        'tincidunt viverra. Sed interdum molestie tincidunt. Etiam ' +
                                        'vitae justo tincidunt, blandit augue id, volutpat ligula. ' +
                                        'Aenean ut aliquet mi. Suspendisse consequat blandit posuere.</p>')

    
    
    def test_create_email_link(self):

        # test created email
        self.assertEqual(
            create_email_link('lbrandon@wharton.upenn.edu'),
            '<a href="mailto:lbrandon@wharton.upenn.edu">lbrandon[aT]wharton.upenn.edu</a>')

        # test created email
        self.assertEqual(
            create_email_link('lbrandon.at.wharton.upenn.edu'),
            '<a href="mailto:lbrandon.at.wharton.upenn.edu">lbrandon.at.wharton.upenn.edu</a>')

if __name__ == '__main__':
    unittest.main()

