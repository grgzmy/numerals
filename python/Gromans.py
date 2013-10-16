
from collections import OrderedDict

#exception
class RomanNumeralException(Exception):
	def __init__(self, msg):
		self.msg = msg
#lists
romans = OrderedDict([('M', 1000), ('D', 500), ('C', 100 ), ('L', 50), ('X', 10), ('V', 5), ('I', 1)])
justRomans = list(romans.keys())

def toRoman(n):
	if n<1 or n>3999:
		raise RomanNumeralException("Invalid range")

	output = ""
	out = [""] * 7

	divs = [0]*7
	i = 0;
	for roman in romans:
		divs[i] = n / romans[roman]
		n%= romans[roman]
		if divs[i] != 0:
			if divs[i] <= 3:
				for p in range(divs[i]):
					out[i]+=roman
			elif divs[i] == 4:
				if divs[i-1]>0:
					out[i] = justRomans[i-2]
				else:
					out[i] = justRomans[i-1]
				out[i-1] = roman
		i+=1
	for o in out:
		output+=o

	return output

def toArabic(r):
	nums = [0]*len(r)
	for i in range(len(r)):
		nums[i] = romans[r[i]]
		if i>0 and nums[i]>nums[i-1]:
			nums[i-1] *= -1
	
	out = 0
	for x in nums:
		out+= x
	return out	

#main
errors = 0
for i in range(100):
	if(i>0):
		try:			
			if i != toArabic(toRoman(i)):
				errors+=1
		except RomanNumeralException, e:
			print e
print "errors: ", errors

