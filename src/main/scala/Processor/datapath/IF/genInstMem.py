path = "src/main/scala/Processor/datapath/IF/"

temp = open(path + "Instructions.txt", "r")

outStr = ""
counter = 1
outStr = outStr + '\twhen (io.addrI === {0}.U){{ io.instruc := "{1}".U }}\n'.format(0, temp.readline().replace('\n', ''))
line = temp.readline()

while len(line) != 0:
    outStr = outStr + '\t.elsewhen (io.addrI === {0}.U){{ io.instruc := "{1}".U }}\n'.format(counter, line.replace('\n', ''))
    counter = counter + 1
    line = temp.readline()

temp.close()

temp = open(path + "InstMem.scala.template", "r")
InstMem = temp.read()
temp.close()


outFile = open(path + "InstMem.scala", "w")
outFile.write(InstMem.replace("PYTHON_REPLACE", outStr))
outFile.close()