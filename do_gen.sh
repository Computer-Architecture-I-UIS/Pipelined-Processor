#!/bin/bash

if [ ! -d generated ]; then
  mkdir generated
fi

rm ./generated/*

if [ ! -z $1 ]; then
	sbt 'runMain Processor.ALUDriver_Verilog'
else
	sbt 'runMain Processor.ALUDriver_SystemVerilog'
fi

rm ./generated/*
mv *.sv ./generated/
mv *.v ./generated/
rm *.json
rm *.fir
rm *.f
