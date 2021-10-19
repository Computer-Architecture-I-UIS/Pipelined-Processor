#!/bin/bash

if [ ! -d generated ]; then
  mkdir generated
fi


rm ./generated/*

mod="Processor."

# Module name to run, defaults to TOP
if [ -n $1 ]; then
	if [[ $1 == "ALU" ]]; then
		mod=$mod"ALUDriver"
	elif [[ $1 == "InstDeco" ]]; then
		mod=$mod"InstDecoDriver"
	elif [[ $1 == "TOP" ]]; then
		mod=$mod"TOPDriver"
	else
		echo "Usage: ./do_gen.sh name [v]"
		echo "Available names: ALU, InstDeco, TOP"
		echo "Generates SystemVerilog by default, use second argument to generate Verilog"
		exit 1
	fi
else
	echo "Usage: ./do_gen.sh name [v]"
	echo "Available names: ALU, InstDeco, TOP"
	echo "Generates SystemVerilog by default, use second argument to generate Verilog"
	exit 1
fi

# Generate SystemVerilog by default
if [ -z $2 ]; then
		mod=$mod"_SystemVerilog"
	else
		mod=$mod"_Verilog"
fi

sbt "runMain $mod"

rm ./generated/*
mv *.sv ./generated/
mv *.v ./generated/
rm *.json
rm *.fir
rm *.f
