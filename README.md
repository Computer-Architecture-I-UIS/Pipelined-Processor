# <p align= "center">Final WorkShop - Pipelined Processor</p>
<p align= "center">
<img src="https://i2.wp.com/www.violetastereo.com/wp/wp-content/uploads/2017/09/UIS-logo1.png" width="30%">
</p>

## Introduction
The launch of the RISC-V Instruction Set Architecture (ISA) in 2011 has allowed an increase in the use of RISC-V in many processors, microcontrollers, among others. Due to its high versatility besides, it is an open ISA (allows further expansion of the instruction set by third parties). Designed by a non-profit foundation, known as the RISC-V Foundation¹. In this project, we show a 3-stage processor design (Fetch, Execute and Memory & WriteBack), as well as the application of the Pipeline technique to the processor. The project will consist of the following sections: Processor, which will show the design of the processor and its most important characteristics; Pipelined Processor, explanation of the Pipeline technique applied to the processor; Formal Verification, this section presents the verification applied to each block of the processor in addition to the TOP circuit; Example Execution, examples of some programs in Assembly and C that are executed in the designed processor; and finally, Conclusions and References.

## Processor
In the figure below, we show our processor. The Datapath allows fetching the instruction in the instruction memory (it stores the whole program or the instructions to be executed), which can be an operation in the ALU or a jump, or a branch. Finally, write the result of the operation or read any data previously stored in memory and give the next instruction to execute through the program counter. On the other hand, the Controlpath has the function of controlling all Datapath activities.

<p align= "center">
<img src="https://github.com/Computer-Architecture-I-UIS/Pipelined-Processor/blob/main/Picture_Reg.png" width="100%">
</p>

Inside of the Datapath, we can find out some digital blocks, for example:
* *Instruction Memory*: To store all instructions to be executed in the processor.
* *Instruction Decode*: It has the function of decode each one instruction before sending this decode instruction to the registers bank.
* *ALU*: To do all the operations arithmetic logical such as addition, subtraction, OR, AND, XOR, shift right, shift left, among others.
* *Memory*: To store output ALU or read any other data storing previous on the memory.

Finally, the TOP circuit designed allows to package all the processor blocks besides, contains the program counter, and the registers banks as well.
 
## Pipelined Processor

### Instruction Fetch
The first stage of the pipeline consist of the program counter and Instruction Memory.

### Execute 
The next stage consist of Instruction Decode, the registers bank and the ALU.

### Write & Memory Back
Only the memory is in the last stage of the pipeline.

## Instruction list
The pipeline processor can execute the following instructions
- ```sll``` - shift left logical
- ```slli``` - shift left logical immediate
- ```srl``` - shift right logical
- ```srli``` - shift right logical
- ```sra``` - shift right arithmetic
- ```srai``` - shift right arithmetic immediate
- ```add``` - add
- ```addi``` - add immediate
- ```sub``` - subtract
- ```xor``` - xor
- ```xori``` - xor immediate
- ```and``` - and
- ```andi``` - and immediate
- ```or``` - or
- ```ori``` - or immediate
- ```slt``` - set
- ```sltu``` - set immediate
- ```slti``` - set unsigned
- ```sltiu``` - set immediate unsigned
- ```lui``` - load upper immediate
- ```auipc``` - add upper immediate to PC

## Formal Verification
The verification of the blocks that constitute the pipelined processor was performed, following the commands shown below.

### Commands to perform formal verification

* First clone the repository with the following command:

```sh
git clone https://github.com/Computer-Architecture-I-UIS/workshop_formal-silva-florez.git
```

* Inside the /Pipelined-Processor folder, open a terminal and run the following commands:

```sh
./do_gen.sh Block_name
```

```sh
Sby -f formal/ Block_name.sby
```
Block_name is replaced by the name of the block to be verified, for example, to verify the ALU the following commands are used.
```sh
./do_gen.sh ALU
```

```sh
Sby -f formal/ ALU.sby
```

## Assembly and C compilation programs
In the following section, you will find a compilation of programs that were created in Assembly and C language, also you will find a little description of each one to understand their functionality.

First of all you need to know that to execute them you need to give permissions to the `talk_processor.s` script by follow:
```
sudo chmod +x talk_processor.sh
```
then the previous script have a simple menu that indicate how execute each program simply execute the next command and the script explain you how it works.
```
./talk_processor.sh
```

### Assembly programs

#### <p align="center"> :man_technologist: Dot Product :man_technologist:
This program can do dot product between two arrays whit a maximum length of 7 positions and give you the result between these two vectors, in the following picture you can see the mathematical expression that was taken into account to develop the program.

<p align= "center">
<img src="https://github.com/Computer-Architecture-I-UIS/Pipelined-Processor/blob/main/dootproduct.png" width="50%">
</p>

#### <p align="center">Numerical series of addition and subtraction</p>
This program begins whit a determined number whose we subtract 9 then to the result we add 5 and so on until obtaining the next data; this process will repeat until the next data be less than 9

#### <p align="center"> :1234: Friendly numbers :1234: </p>
Defining d(n) as the sum of the proper divisors of n, if d(a) = b and d(b) = a, where a is different from b, then a and b are a pair of friendly numbers. The program finds the smallest pair of friendly numbers.

***Example***

To be more clear what is a friendly number we take the numbers 220 and 284
* The proper divisors of 220 are 1, 2, 4, 5, 10, 11, 20, 22, 44, 55, and 110, which add up to 284
* The proper divisors of 284 are 1, 2, 4, 71, and 142, which add up to 220

#### <p align="center"> :symbols: Collatz conjeture :symbols: </p>
This conjecture establishes that when applying to any number n the equation shown below will have a decomposition of itself decreasing its value until reaching the sequence 4, 2, 1.

<p align= "center">
<img src="https://github.com/Computer-Architecture-I-UIS/Pipelined-Processor/blob/main/collatz.png" width="30%">
</p>

***Example***

Let's look at the case for n = 13: 13, 40, 20, 10, 5, 16, 8, 4, 2, 1

### C programs

#### <p align="center"> :horse: Horse Movement :racehorse: </p>
This program describe the possibilities that have a horse in the chessboard, the script contains a series of conditionals that ensure the right positions that the horse can take, in the following picture you can see two of many cases that can present the horse, one of them is in a position ***[5,5]*** and ***[2,2]***

<p align="center">
  <img src="https://github.com/Computer-Architecture-I-UIS/Pipelined-Processor/blob/main/5x5.png" width="40%" />
  <img src="https://github.com/Computer-Architecture-I-UIS/Pipelined-Processor/blob/main/2x2.png" width="40%" /> 
</p>

#### <p align="center"> :chart_with_downwards_trend: Greatest common divisor :chart_with_upwards_trend: </p>
Given a and b two nonzero integers. If a number c divides a and b, that is, c|a and c|b, we will say that c is the common divisor of a and b. Note that any two integers have common divisors.

***Example***

12 is the GCF of 36 and 60. Well, 12|36 and 12|60; in turn 12 is divisible by 1, 2, 3, 4, 6, and 12 which are common divisors of 36 and 60

#### <p align="center"> :file_folder: Array ordering :clipboard: </p>

El programa ordena una lista de 16 enteros de 8 bits sin signo de mayor a menor (list[0] contiene el mayor, list[15] contiene el menor), la lista contiene valores predefinidos al azarg

***Example***

Assume that in list we have the following numbers [9, 16, 15, 2, 3, 14, 7, 1, 5, 13, 11, 12, 8, 6, 10, 4] and then executing the program at the output we have [16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1].

#### <p align="center"> :one: Prime number :seven: </p>
A prime number is a natural number greater than 1 that has only two distinct positive divisors: itself and 1.
The algorithm consists of entering a number n and the program must determine if it is prime or not because the module function of the division cannot be used, the solution that arises is to make the subtraction of n with the values that They go from 2 to n -1 (i) and comparing that said subtraction is not less than the i with which it is being subtracted, if the number is less it means that n is not divisible by i, therefore, it continues with the next number, If once i got to - 1 and I found that said subtractions none reached zero, then the number is prime, if on the contrary if any of the subtractions gave zero then the number is divisible by said number i and therefore the number is not is prime or not.

## Conclusions

## References

* [1] Riscvbook.com, 2021. [Online](http://riscvbook.com/spanish/guia-practica-de-risc-v-1.0.5.pdf) [Accessed: 20- Sep- 2021].
* [2] Home.ustc.edu.cn, 2021. [Online](http://home.ustc.edu.cn/~louwenqi/reference_books_tools/Computer%20Organization%20and%20Design%20RISC-V%20edition.pdf) [Accessed: 19- Oct- 2021].
* [3] Imm.dtu.dk, 2021. [Online](http://www.imm.dtu.dk/~masca/chisel-book.pdf) [Accessed: 19- Oct- 2021].
* [4] https://github.com/Computer-Architecture-I-UIS/full-processor-maryteam.git
* [5] https://github.com/Computer-Architecture-I-UIS/the-processor-maryteam.git


