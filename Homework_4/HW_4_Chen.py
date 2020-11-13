'''
Name: Bowen Chen
Penn ID: 46297683
Statement of work: attended the professor's office hour and Kai's office hour; used Github user tsfu's resource for references; discussed with Wangjin Li for potential
ideas regarding the logic for computer_play. Otherwise, I worked by myself.
'''
import random

def setup_bricks():
    """
    Created a main pile of 60 bricks, represented as a list containing the integers 1 – 60. Created a discard pile of 0 bricks, 
    represented as an empty list. This function returns both lists.
    """
    main_pile = []
    for i in range(1,61):
        main_pile.append(i)
    discard = []
    return main_pile, discard


def shuffle_bricks(bricks):
    """
    Shuffle the given bricks (represented as a list) without a return value.
    """
    import random
    random.shuffle(bricks)


def check_tower_blaster(tower):
    """
    This is function to determine whether we have a sucessfully elected tower according to the instruction.
    """
    if tower == sorted(tower):
        return True
    else:
        return False

def check_bricks(main_pile,discard):
    """
    Check if there are any cards left in the given main pile of bricks. If not, shuffle the discard pile (using the shuffle function) 
    and move those bricks to the main pile. Then turn over the top card to be the start of the new discard pile. Then turn over the top 
    card to be the start of the new discard pile.

    """ 

    if len(main_pile) == 0: #when there is no bricks left then the length of main pile would come to 0
        shuffle_bricks(discard)
        for i in discard:
            main_pile.append(i)
        discard = []
        discard.append(get_top_brick(main_pile))
    

def get_top_brick(brick_pile):
    """
    It is used at the start of game play for dealing bricks. This function will also be used during 
    each player’s turn to take the top brick from either the discarded brick pile or from the main pile.
    This function must return an integer.
    """

    very_top = brick_pile[0]
    brick_pile.pop(0)
    return very_top

def deal_initial_bricks(main_pile):
    """
    This function is designed to give out ten bricks to each player from the main pile and it is stored as
    computer bricks and player bricks respectively.
    """
    player_bricks = []
    comps_bricks = []
    for i in range(0,20):
        if i % 2 == 0:
            comps_bricks.insert(0,get_top_brick(main_pile))
        else:
            player_bricks.insert(0,get_top_brick(main_pile))
    return comps_bricks, player_bricks

def add_brick_to_discard(brick, discard):
    """
    Add the given brick (represented as an integer) to the top of the given discard pile (which is a list).
    This function does not return anything.
    """
    discard.insert(0,brick)



def find_and_replace(new_brick, brick_to_be_replaced, tower, discard):
    """
    Identify which brick needs to be replaced and obtain its index, afterwards assign the new brick to the slot
    """
    if brick_to_be_replaced in tower:
        position = tower.index(brick_to_be_replaced)
        add_brick_to_discard(brick_to_be_replaced,discard)
        tower[position] = new_brick
        return True
    else:
        return False




def computer_play(tower, main_pile, discard):
    """
    This function is where the computer's action takes place. It uses a series of functions describe below and it also decides whether 
    to take brick from the discard pile, the main pile, or not to take bricks at all. It works by determining by whether making the change
    would actually lower the sum of all indices. The process starts at the begining
    """
    if yes_or_no(tower, discard[0], discard): #this decides whether to draw from the discard pile or the main pile
        #print('Choose', discard[1], 'from discard.')
        discard.pop(1) #making sure that the first one on top is cleared when the brick is taken from discard
    elif yes_or_no(tower, main_pile[0], discard):
        print('Choose', get_top_brick(main_pile), 'from main_pile.')
    else: #here it describes the condition where no card is taken
        add_brick_to_discard(get_top_brick(main_pile), discard)
        #print('The computer did not do anything because its suboptimal.')

def yes_or_no(tower, new_brick, discard):
    """
    This function returns true and false. If the minimum of the sum of the indices is less than that of the original, change will take place.
    If the two numbers are equal, we would calculate their deviation to a benchmark value. If the new brick has a lower deviation, we would let
    the change to take place. 
    """
    original_count = count(tower,sorted(tower)) #this is for comparison 
    count_lst = [0]*10 #introduce a list with 10 zeroes that will store the change in indices at each position
    for i in range(0,10):
        tower_copy = [i for i in tower] #create a copy of tower since we don't want to change the actual tower at the very beginning
        tower_copy[i] = new_brick #slide in the new brick at every position from 0 to 9 and record how much would the indices change at each position
        count_lst[i] = count(tower_copy,sorted(tower_copy)) #each element of the ealier list will now have a number 
    if min(count_lst) < original_count: #min tells us where this change would incurr the least amount of changes in terms of the indices 
        find_and_replace(new_brick, tower[count_lst.index(min(count_lst))], tower, discard) #locate its position in tower and update it
        return True #if this true the brick will be taken from discard
    elif min(count_lst) == original_count: #if the numerical value in terms of indices change will be the same as the original we use a different method
        abs(tower[count_lst.index(min(count_lst))]-(count_lst.index(min(count_lst))+1)*6) > abs((new_brick - (count_lst.index(min(count_lst))+1)*6)) #if the 
        #abs value of a selected number minus a benchmark is greater than the abs value of the incoming brick minus the benchmark
        #we would use replace the position with a new brick because it gives a better spacing for winning
        find_and_replace(new_brick, tower[count_lst.index(min(count_lst))], tower, discard)
        return True
    else:
        return False


def count(before_lst, after_lst):
    """
    This is a function where we quantify how much changes in terms of unit of indices have been made
    """
    count_num = 0 
    for k in range(0,10):
        count_num += abs(after_lst.index(before_lst[k]) - k)
       
    return count_num


def player_draw_discard(tower,discard,discard_spot):
    top_discard = get_top_brick(discard)
    find_and_replace(top_discard,tower[discard_spot],tower,discard)
    print('The new brick has been added from the discard pile, and your tower looks like this: ', tower)
    return tower

def player_draw_main(top_pile, tower, discard, pile_spot):
    find_and_replace(top_pile,tower[pile_spot], tower, discard)
    print('The new brick has been added from the main pile, and your tower looks like this: ', tower)
    return tower
            


def main():
    
    print('**************************************')
    print('**************************************')
    print('')
    print("*** It's time to blast some TOWERS ***")
    print('')
    print('**************************************')
    print('**************************************')
    game_over = False
    main_pile, discard = setup_bricks()   
    shuffle_bricks(main_pile) 
    player_tower, comp_tower = deal_initial_bricks(main_pile)

    discard.append(get_top_brick(main_pile))

    print('Your tower looks like this: ', player_tower)
    print ('''The computer's tower looks like this: ''', comp_tower)

    while not game_over:
        check_bricks(main_pile, discard)
    
    #computer's turn 
        print('&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&')
        print("    It's the computer'sturn now!      ")
        print('&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&')
        print('The computer has finished updating is tower.')
        #print('Computer tower before:', comp_tower)  
        #print(main_pile[0:5],discard)
        #used for debugging
        computer_play(comp_tower, main_pile, discard)
        print("The computer has just updated its tower.")
        #print('Computer tower after:', comp_tower)
        #used for debugging
        if check_tower_blaster(comp_tower):
            print('The computer has completed its tower. Sorry you just lost this round of the game.')
            #while condition ends here
            break
    
    #players turn 
        print('**************************************')
        print("         It's your turn now!          ")
        print('**************************************')
        print('You current tower looks like this: ', player_tower)
        print('The first brick off the discard pile is: '+ str(discard[0]))
        #print('discard: ', discard)  mainly used for debugging
        #print(main_pile[0:5],discard) 
        discard_response = input('Would you like to use ' + str(discard[0]) + ' in your tower? Please answer Y for yes and N for no: ')
        while discard_response != 'Y' and discard_response != 'N':
            discard_response = input('Sorry your input is not valid. Please respond with Y for yes and N for no: ')
        if discard_response == 'Y':
            discard_spot = int(input('Where would you like to place your brick, please select a number from 0 to 9: '))
            while not discard_spot in range(0,10):
                    discard_spot = int(input('Your input earlier is not valid, please try again and select a number from 0 to 9: '))
            player_draw_discard(player_tower, discard, discard_spot)
        else:
            top_pile = get_top_brick(main_pile)
            print('The brick from the main pile appears to be: ', top_pile)
            pile_response = input('Would you like to use this brick from the main pile in your tower? Please answer Y for yes and N for no: ')         
            while pile_response != 'Y' and pile_response != 'N':
                pile_response = input('Sorry your input is not valid. Please respond with Y for yes and N for no: ')
            if pile_response == 'Y':
                pile_spot = int(input('Where would you like to place your brick, please select a number from 0 to 9: '))
                while not pile_spot in range(0,10):
                    pile_spot = int(input('Your input earlier is not valid, please try again and select a number from 0 to 9: '))
                player_draw_main(top_pile, player_tower, discard, pile_spot)
            else:
                add_brick_to_discard(top_pile,discard)
                print('It seems that you decided not to update your tower and thus this turn comes to an end, your tower looks like this: ', player_tower)
        if check_tower_blaster(player_tower):
            print("You have completed tower! Congratulations, you just won the game!") #while condition ends here
            break
        
if __name__ == '__main__':
    main()