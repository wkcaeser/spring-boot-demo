#!/usr/bin/env bash

find_str=$1

file_tree=`find $2 | egrep -v *.git* | egrep -v */lib* | egrep *src*`
files=[]

num=0
for filename in ${file_tree}
do
    if test -f "$filename"; then
        if test ${filename##*.} != 'png' &&
        test ${filename##*.} != 'rb' &&
        test ${filename##*.} != 'gif' &&
        test ${filename##*.} != 'html' &&
        test ${filename##*.} != 'css' &&
        test ${filename##*.} != 'js' &&
        test ${filename##*.} != 'woff' &&
        test ${filename##*.} != 'pkgdef' &&
        test ${filename##*.} != 'idx' &&
        test ${filename##*.} != 'pack' &&
        test ${filename##*.} != 'sample' &&
        test ${filename##*.} != 'dll'; then
            echo ${filename}
            files[num]=${filename}
            num=$(($num+1))
        fi
    fi
done

echo ${num}

echo "" > ~/filter_answer.log
for file in ${files[@]}
do
    echo ${file}
#   %d{yyyy-MM-dd HH:mm:ss} %p [USER_ID:%X{USER_ID}][%t][%L][%c.%M]%m%n
    rs=$(cat ${file} | fgrep "${find_str}")
    if test ${#rs} -gt 0; then
        echo ${file} >> ~/filter_answer.log
        echo ${file}
    fi
done

echo "----------------------------------------------------------------------------------"
echo "----------------------------------------------------------------------------------"
echo "-------------------------------results--------------------------------------------"
echo "----------------------------------------------------------------------------------"
cat ~/filter_answer.log
