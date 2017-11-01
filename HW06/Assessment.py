# Q1
def FizzBuzz(lalist):
    end_list = []
    try:
        for element in lalist:
            if (element % 3 == 0 and element % 5 == 0):
                end_list.append('FizzBuzz')
            elif (element % 3 == 0):
                end_list.append('Fizz')
            elif (element % 5 == 0):
                end_list.append('Buzz')
            else:
                end_list.append(element)
        return end_list
    except Exception:
        print('Invalid input')

#Q2
def bitMask(inp):
    rep = bin(inp)
    rep = rep[2:len(rep) - 1]

    if (rep[len(rep) - 3] == '1' or rep[len(rep) - 13] == '1'):
        return True
    else:
        return False

#Q3
# the output is 1. The programmer could've simplified the conditions in the if statements.
# Additionally a mathematical expression for that logic can be derived (which I didn't have time for)
# Recursion is very expensive in terms of memory so the mathematical expression would significantly improve efficiency

#Q4 - A
def horse(bolist):
    p1sc = 0
    p2sc = 0

    for idx, shot in enumerate(bolist):
        if idx < len(bolist) - 1:
            if (shot == True and bolist[idx + 1] == False):
                if(idx % 2 != 0):
                    p1sc += 1
                elif(idx % 2 == 0):
                    p2sc += 1
            if (p1sc == 5 or p2sc == 5):
                break

    return True if (p1sc == 5 or p2sc == 5) else False

#Q4 - B

class HorseHandler(object):
    score = 0
    word = 'HORSE'

    def getScore(self):
        return score

    def addPoint(self, hit):
        if hit:
            score += 1

    def getLetterRepresentation(self):
        return word[0:score]
