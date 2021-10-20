while IFS= read -r line
do
  echo h${line:1:8}
  echo h00000013
  echo h00000013
  echo h00000013
done < boot.mem > Instructions.txt