'''
Guess Number Game : Origninal
'''

'''
def fun(s):
    if s.isdigit() and 1<= int(s) <=100:
        return True
    else:
        return False

def main():
    n=random.randint(1,100)
    gn=False
    g=input("Guess a number between 1 and 100:")
    ng=0
    while not gn:
        if not fun(g):
            g=input("I wont count this one Please enter a number between 1 to 100")
            continue
        else:
            ng+=1
            g=int(g)

        if g<n:
            g=input("Too low. Guess again")
        elif g>n:
            g=input("Too High. Guess again")
        else:
            print("You guessed it in",ng,"guesses!")
            gn=True
'''


'''
Guess Number Game : Refactored
'''
import random
def is_valid_input(s):
    if s.isdigit() and 1<= int(s) <=100:
        return True
    else:
        return False

def main():
    
    target_number = random.randint(1,100)
    has_guessed_correctly = False
    guess_count = 0

    user_input = input("Guess a number between 1 and 100:")
    
    while not has_guessed_correctly:
        if not is_valid_input(user_input):
            user_input = input("I wont count this one Please enter a number between 1 to 100")
            continue
        else:
            guess_count += 1
            guessed_value = int(user_input)

        if guessed_value < target_number:
            user_input = input("Too low. Guess again")
        elif guessed_value > target_number:
            user_input = input("Too High. Guess again")
        else:
            print("You guessed it in", guess_count, "guesses!")
            has_guessed_correctly = True