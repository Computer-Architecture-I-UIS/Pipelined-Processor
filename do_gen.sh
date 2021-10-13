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
	else
		echo "Available names: ALU, InstDeco"
		exit 1
	fi
else
	# Generate TOP by default
	echo "TODO TOP"
	mod=$mod"TOP"
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
