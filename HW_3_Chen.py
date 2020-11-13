
'''
Name: Bowen Chen
Penn ID: 46297683
Statement of work: attended Chenyun Wei's office hour and Tom Lee's office hour; used Github user sophieluo's resources and glenyeh's resources for reference.
Otherwise, I worked by myself.
In this game, the main goal is to have the computer and the player react with one another accordingly to increase their chance to win the game.
'''
import random
"""
Here we import the random module so we can use the randint function.
"""

def main():
#This is the main function, and here using a while loop can help undate the value each turn.
#At the very end, we would like to see the results being displayed and that's why we are putting it at the end.
    print_instructions()
    computer_score = 0 #initialize a number of parameters that will be introduced later
    human_score = 0
    game_over = False
    while not game_over:
        computer_score += computer_move(computer_score, human_score)
        human_score += human_move(computer_score,human_score)
        game_over = is_game_over(computer_score,human_score)

    show_final_results(computer_score,human_score)

def print_instructions():

#This function helps reader to understand what the rules are.

    print('''Pig is a very simple game. Two players take turns; on each turn, a player rolls a six-sided \n
    die ("die" is the singular of "dice") as many times as she wishes, or until she rolls a 6.  \n
    Each number she rolls, except a 6, is added to her score this turn; but if she rolls a 6, her \n
    score for this turn is zero, and her turn ends. At the end of each turn, the score for that \n
    turn is added to the player's total score. The first player to reach or exceed 50 wins.
    \n
    For example: \n
    Alice rolls 3, 5, 3, 1, and stops. Her score is now 12. \n
    Bob rolls 5, 4, 1, 1, 2, and stops. His score is now 13. \n
    Alice rolls 5, 3, 3, 5, 4, and stops. Her score is now 32 (12 + 20). \n
    Bob rolls 4, 6. He has to stop, and his score is still 13 (13 + 0). \n
    etc. \n
    \n
    \n
    It is about time.''')

def computer_move(computer_score, human_score):

#This functions entails how the computer decide to play under different condition.
#Basically, when the computer is leading, it will roll three dices. While it is
#behind, the computer will toss four dices. The chances of having one 6 out of three
#dices if (5/6)^3 which is approximately 57%. In the case of rolling four dices, such
#probability decreases to 48%. This way, the computer is willing to take more risks
#when it is behind. When it is ahead, it does not entirely averse risk. Overall, the
#strategy is more on the conservative side.
    print(''' \n
             &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& \n
             &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& \n
             \n
                  It's the computer's turn now \n
             \n
             &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& \n
             &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& \n
             ''')
    computer_total = 0 #initialize variable used for counting
    if computer_score >= human_score:
        for i in range(0,3): #rolling 3 dices
            computer_dice_result = roll()
            if computer_dice_result == 6: #when a 6 is observed, the process stops here
                print('Ooooooops, the computer has rolled a 6 during this turn.')
                return 0 #score this turn goes to 0
            else:
                computer_total += computer_dice_result
    else:
        for i in range(0,4): #rolling 3 dices when the computer is behind
            computer_dice_result = roll()
            if computer_dice_result == 6:
                computer_total = 0
                print('Ooooooops, the computer has rolled a 6 in its turn.')
                return 0 #score this turn goes to 0
            else:
                computer_total += roll()

    print('The computer has scored ' + str(computer_total) + ' point(s) this round.')
    return computer_total


def human_move(computer_score, human_score):

#This function describes situations when the player rolls different values. Since the
#game specifies that each player must roll at least once per turn, it is designed that
#each player will roll the dice automatically at the beginning. Afterwards, the player
#gets to decide whether they want to stop or continue.
    print(''' \n
             *************************************** \n
             *************************************** \n
             \n
                     It's your turn now \n
             \n
             *************************************** \n
             *************************************** \n
             ''')
    show_current_status(computer_score, human_score) #call this function according to instruction given
    human_score_this_turn = 0 #initialize this parameter
    rolled_first = roll()
    if rolled_first != 6: #if the automatic roll is not 6, we can record it move on to the next step.
        human_score_this_turn = rolled_first
        human_score += human_score_this_turn
        print('Since each player must roll at least one time per turn, you rolled a ' + str(rolled_first) + ' on the first try by automation.')
        print('That will put you at ' + str(human_score) + ' point(s) if you decide to stop now.')

        while ask_yes_or_no(): #this loops help determine what the score is at the end of each turn
            human_dice_result = roll()
            print('It appears you just rolled a ' + str(human_dice_result) + '.') #the player gets to decide whether they want to roll more dices
            if human_dice_result == 6: #if one 6 is observed the turn ends here
                print('Since you rolled a 6, your score for this turn is 0.')
                return 0 #value goes to 0
            else:
                human_score_this_turn += human_dice_result
                print('Your score for this turn will add up to ' + str(human_score_this_turn) + ' if you decide to stop now.')
    else: #this describes the situation when the first roll is 6
        print('Unfortunately, it appears you rolled a 6 during the automatic round and your turn ends now with a score of zero.')
        print('Your score this turn remains ' + str(human_score) + ' point(s).')
        print('You can simply put in anything and it will not affect your result since you rolled a 6 earlier.')
        while ask_yes_or_no():
            return 0

    print('The total score for this turn is ' + str(human_score_this_turn) + ' points.')
    return human_score_this_turn


def show_current_status(computer_score, human_score):
#This compares the computer score with the human score and put it on display

    print('The computer has scored ' + str(computer_score) + ', and you have scored ' + str(human_score) + '.')
    if human_score - computer_score > 0: #inform the player how he/she is doing
        print('You are leading by ' + str(abs(computer_score - human_score)) + ' point(s).')
    elif computer_score - human_score == 0:
        print('Now the scores are tied.')
    else:
        print('You are behind by ' + str(abs(computer_score - human_score)) + ' point(s).')

def ask_yes_or_no():
#This function help the player to decide whether they want to continue to roll during their turn

    response = input('Would you like to roll the dice again? Input Y or y for yes or N and n for no:')

    if response == 'Y' or response == 'y':
        return True
    elif response == 'N' or response == 'n':
        return False
    else:
        print('Sorry, your input is not valid. Please try again.')
        
        return ask_yes_or_no()


def roll():
#This function works as a random number generator from 1 to 6

    return random.randint(1,6)

def is_game_over(computer_score, human_score):
#This function helps us determine what the final scores are, and whether the winning condition has been achieved.'''

    if computer_score == human_score:
        return False
    elif max(computer_score, human_score) >= 50:
        return True
    else:
        return False

def show_final_results(computer_score, human_score):
#This function displays final result at the end, and it will be called in main.'''

    if human_score > computer_score:
        print ('Congrats, you won the game by ' + str(abs(human_score - computer_score)) + '. Thanks for playing.')
    else:
        print ('Sorry, you lost the game by ' + str(abs(human_score - computer_score)) + '. Thanks for playing!')
    try_again() #call the try_again function

def try_again():
#This function ask the player whether they want to continue to play.'''

    z = input('Do you want to try again? Plesase answer Y or y for yes and N or n for no:')

    if z == 'Y' or z == 'y':
        print('You got the mamba mentality, let us try again!')
        main()
    elif z == 'N' or z == 'n':
        print('Thanks for playing this game.')
#In case the user types in something other than the acceptable answer
    else:
        print('Only Y,y,N,n are acceptable answers, please enter your answer again:')
        try_again()

main()
