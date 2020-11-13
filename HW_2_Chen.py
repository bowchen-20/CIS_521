'''
Name: Bowen Chen
Penn ID: 46297683
Statement of work: attended Chenyun Wei's TA section/used Github user zn16's resources for reference.
Input that leads to win: [0, 0, 0, 0, 0, 0, 0, 0, 0 ,0, 60, 40]
Input that leads to loss:[10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]

This is a code designed for simulating the landing process of a spaceship.
Gravity is pulling it down while the player has the option to burn fuel 
to slow the process down so that the landind velocity is less than 10m/s.
'''

def Lunar_lander():
    print('***Welcome abroad captain***')
    print('***RIP Kobe Bryant***')
#initial condition stated here
    current_parameters = {'Altitude':100, 'Velocity':0, 'Fuel available':100}
    Kobe = 1
    print('The current altitude is '+ str(current_parameters['Altitude'])+' m')
    print('The current velocity is '+ str(current_parameters['Velocity'])+' m/s')
    print('The current fuel left is '+ str(current_parameters['Fuel available'])+' L')
    print('Now initiate landing process')
    t = 0

#starting the loop
    while current_parameters['Altitude'] > 0:
#In case the input is not a number, ask the user to try again;
#If it is a valid input, go into the details
        try:
            x = float((input('How much fuel you wish to burn?')))
            Kobe = 0
        except ValueError:
            print('Your input is not valid, please try again.')
            continue
        if x < 0:
            x = 0
        elif x > current_parameters['Fuel available']:
            x = current_parameters['Fuel available']
            current_parameters['Fuel available'] = 0
        else:
            current_parameters['Fuel available'] -= x

        current_parameters['Velocity'] += 1.6
        current_parameters['Velocity'] -= 0.15 * (x)
        current_parameters['Altitude'] -= current_parameters['Velocity']

#Landing conditions stated in the document. If the final velocity
#is greater than 10 then it is not a safe landing
        if current_parameters['Altitude'] <= 0:
            if current_parameters['Velocity'] <= 10:
                print('Your landing is safe, smooth as a Kobe fadeaway. The landing condition is shown as following:')
                print('The final velocity is '+ str(current_parameters['Velocity'])+' m/s')
                print('The lefover fuel is '+ str(current_parameters['Fuel available'])+' L')
                print('The time taken is ' + str(t) + ' s')
                Kobe = 0
            
            else:
                print('Sorry your landing was not safe.')         
                Kobe = 0
        else:    
            print('The current altitude is '+ str(current_parameters['Altitude'])+' m')
            print('The current velocity is '+ str(current_parameters['Velocity'])+' m/s')
            print('The current fuel left is '+ str(current_parameters['Fuel available'])+' L')
            t += 1
            Kobe = 1
            
#At the end of the game, ask the player whether they want to make another attempt 
def try_again():
    z = input('Do you want to try again? Plesase answer Y or y for yes and N or n for no:')
    
    if z == 'Y' or z == 'y':
        print('You got the mamba mentality, let us try again!')
        Lunar_lander()
        try_again()
        
    elif z == 'N' or z == 'n':
        print('Thanks for playing this game.')
        
#In case the user types in something other than the acceptable answer     
    else:
        print('Only Y,y,N,n are acceptable answers, please enter your answer again:')
        try_again()
    
#calling the function
Lunar_lander()    
try_again()
