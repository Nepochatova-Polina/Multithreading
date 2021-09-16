package main

import (
	"fmt"
	"sync"
)

var waitGroup sync.WaitGroup
var resultLineID int = 0

type line struct {
	id   int
	line [50]int
}

func main() {
	currentLineCh := make(chan line, 50)

	var array [50]int
	for i := 0; i < 50; i++ {
		for j := 0; j < 50; j++ {
			if i == 28 && j == 18 {
				array[18] = 1
			} else {
				array[j] = 0
			}
		}
		currentLineCh <- line{i, array}
	}
	for i := 1; i < 3; i++ {
		waitGroup.Add(1)
		go findValueInMatrix(currentLineCh)
	}
	waitGroup.Wait()
}

func findValueInMatrix(currentLineCh chan line) {
	for resultLineID == 0 {
		line := <-currentLineCh
		for i := 0; i < 50; i++ {
			if line.line[i] == 1 {
				resultLineID = line.id
				fmt.Println("result find in line #:", line.id)
				fmt.Println(line.line)
				break
			}
		}
	}
	defer waitGroup.Done()
	fmt.Println("routine finished")
}
