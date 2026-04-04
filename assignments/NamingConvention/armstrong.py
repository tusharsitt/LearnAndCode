'''
Armstrong Number Checker : Original
'''

'''
def fun(N):
    # Initializing Sum and Number of Digits
    s = 0
    t = 0

    # Calculating Number of individual digits
    t2 = N
    while t2 > 0:
        t = t + 1
        t2 = t2 // 10

    # Finding Armstrong Number
    t2 = N
    for n in range(1, t2 + 1):
        R = t2 % 10
        s = s + (R ** t)
        t2 //= 10
    return s


# End of Function

# User Input
N2 = int(input("\nPlease Enter the Number to Check for Armstrong: "))

if (N2 == fun(N2)):
    print("\n %d is Armstrong Number.\n" % N2)
else:
    print("\n %d is Not a Armstrong Number.\n" % N2)
'''

'''
Armstrong Number Checker : Refactored
'''

def calculate_armstrong_sum(number):
    sum_of_powers = 0
    digit_count = 0

    temporary_value = number
    while temporary_value > 0:
        digit_count = digit_count + 1
        temporary_value = temporary_value // 10

    temporary_value = number
    for _ in range(1, digit_count + 1):
        current_digit = temporary_value % 10
        sum_of_powers = sum_of_powers + (current_digit ** digit_count)
        temporary_value //= 10

    return sum_of_powers



number_to_check = int(input("\nPlease Enter the Number to Check for Armstrong: "))

if (number_to_check == calculate_armstrong_sum(number_to_check)):
    print("\n %d is Armstrong Number.\n" % number_to_check)
else:
    print("\n %d is Not a Armstrong Number.\n" % number_to_check)  