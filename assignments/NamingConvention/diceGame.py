
'''
Roll the Dice : Original
'''

'''
import random
def fun(s):
    n=random.randint(1, s)
    return n


def main():
    s=6
    r1=True
    while r1:
        r2=input("Ready to roll? Enter Q to Quit")
        if r2.lower() !="q":
            n=fun(s)
            print("You have rolled a",n)
        else:
            r1=False

'''

'''
Roll the Dice : Refactored
'''

import random

def generate_random_integer(lower_limit, upper_limit):
    random_integer = random.randint(lower_limit, upper_limit)
    return random_integer


def main():
    highest_count_on_dice = 6
    lowest_count_on_dice = 1
    is_running = True

    while is_running:
        user_input = input("Ready to roll? Enter Q to Quit: ")
        
        if user_input.lower() != "q":
            dice_roll_result = generate_random_integer(lowest_count_on_dice, highest_count_on_dice)
            print("You have rolled a", dice_roll_result)
        else:
            is_running = False



