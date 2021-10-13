#!/bin/sh

echo "-------------------------Please talk to me--------------------------"
echo "You have the next options to execute the programs whit the processor"
echo "--------------------------------------------------------------------"
echo "-----Type only the name of the program that you want to execute-----"
echo "1. horse"
echo "2. MCD"
echo "3. order"
echo "4. primos"
echo "5. dotproduct"
echo "6. serie"
echo "7. amicableFast"
echo "8. collatz"
while :
do
  read INPUT_STRING
  case $INPUT_STRING in
	horse)
		echo "Executing horse script..."
		./compile.sh c_program/horse.c
		cd verilog
		iverilog -o test *.v
		vvp test
		gtkwave test.vcd &
		cd ..
		
		break
		;;
	MCD)
		echo "Executing MCD script..."
		./compile.sh c_program/MCD.c
		cd verilog
		iverilog -o test *.v
		vvp test
		gtkwave test.vcd &
		cd ..
		
		break
		;;
	order)
		echo "Executing order script..."
		./compile.sh c_program/order.c
		cd verilog
		iverilog -o test *.v
		vvp test
		gtkwave test.vcd &
		cd ..
		
		break
		;;
	primos)
		echo "Executing primos script..."
		./compile.sh c_program/primos.c
		cd verilog
		iverilog -o test *.v
		vvp test
		gtkwave test.vcd &
		cd ..
		
		break
		;;
	dotproduct)
		echo "Executing dotproduct script..."
		./doasm.sh assembly/projects/dotproduct.S
		cd verilog
		iverilog -o test *.v
		vvp test
		gtkwave test.vcd &
		cd ..
		
		break
		;;
	serie)
		echo "Executing serie script..."
		./doasm.sh assembly/projects/Serie.S
		cd verilog
		iverilog -o test *.v
		vvp test
		gtkwave test.vcd &
		cd ..
		
		break
		;;
	amicableFast)
		echo "Executing amicableFast script..."
		./doasm.sh assembly/projects/amcableFast.S
		cd verilog
		iverilog -o test *.v
		vvp test
		gtkwave test.vcd &
		cd ..
		
		break
		;;
	collatz)
		echo "Executing collatz script..."
		./doasm.sh assembly/projects/collatz.S
		cd verilog
		iverilog -o test *.v
		vvp test
		gtkwave test.vcd &
		cd ..
		
		break
		;;
  esac
done
echo 
echo "That's all, thank you!"
