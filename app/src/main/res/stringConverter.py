import xml.etree.ElementTree as ET
# This document creates a file enStrings.txt which contains all the strings that need to be translated
# It then takes the contents of trStrings.txt and replaces the values of file string-es.xml via a 1-to-1 matching. We can copy it over to values-es.
# run with: python stringConverter.py
enStrings = open("enStrings.txt", "w")

root = ET.parse('values/strings.xml').getroot()

for string in root:
    #name = string.get('name')
    val = string.text
    if val == None:
        val = ''
    enStrings.write(val+'\n\n')
    # This will cause issues if a string already includes this. There has to be a clear way to specify the end of a string...
enStrings.close()

# Just use the root from the first part and edit it to write the finished xml
trStrings = open("trStrings.txt", "r", encoding="UTF-8")
lines = trStrings.readlines()
lineNum = 0

removeString = []
for string in root:
    #print(string.get("name"))
    if lineNum < len(lines) and string.text != lines[lineNum][:-1]:
        string.text = lines[lineNum][:-1]    
    else:
        # If we don't have a different translation, it should use the default
        removeString.append(string)
        # removing it here messes with the iteration of the loop

    # To count over the two endlines we added above
    lineNum += 2

for string in removeString:
    root.remove(string)

tree = ET.ElementTree(root)
tree.write("values-b+es/strings.xml")
trStrings.close()

print('done')