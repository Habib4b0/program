netstat -ntlp | awk '$4~/:*8015$/{gsub(/\/.*/,"",$NF);cmd="kill -9 "$NF;system(cmd)}'
echo "Service Stopped Successfully"