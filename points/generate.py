import random

# The starting number of points
START = 4
# The stopping number of points inclusive
STOP = 30

# Size of graph that the points will be generated on
# 4 points * CONSTANT_FACTOR of 1 = -4 to 4 in both axes
CONSTANT_FACTOR = 2

# Choose the delimiter of the points
DELIMETER = " "

count = START

filename = ""

for count in range (START,STOP + 1,1):
	filename = "" + str(count) + ".txt"
	with open(filename, 'w') as output:
		line = 0
		output.write(str(count) + "\n")
		for line in range(1,count + 1,1):
			randX = random.randint(-1 * CONSTANT_FACTOR * count, CONSTANT_FACTOR * count)
			randY = random.randint(-1 * CONSTANT_FACTOR * count, CONSTANT_FACTOR * count)
			outputline = "" + str(randX) + DELIMETER + str(randY)
			output.write(outputline + "\n")
print ("\n\nDone!\n\n")
