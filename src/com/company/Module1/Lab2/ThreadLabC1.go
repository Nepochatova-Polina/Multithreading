package main

import (
	"fmt"
	"math/rand"
	"sync"
)

var wg sync.WaitGroup
var currentLine int = -1
var result int = 0
var matrix [50][50]int

func main() {
	for i := 0; i < 50; i++ {
		for j := 0; j < 50; j++ {
			matrix[i][j] = 0
		}
	}
	//matrix[22][15] = 1
	matrix[rand.Intn(50)][rand.Intn(50)] = 1

	for i := 1; i < 3; i++ {
		wg.Add(1)
		go findValue()
	}
	wg.Wait()
	fmt.Println("Result find in line #:", result)
	fmt.Println("The End")

}

func getNextLine(mutex *sync.Mutex) int {
	mutex.Lock()
	currentLine++
	mutex.Unlock()
	return currentLine
}

func findValue() {
	var mutex sync.Mutex
	for result == 0 {
		currentID := getNextLine(&mutex)
		fmt.Println("Routine is working with the line #:", currentID)
		for j := 0; j < 50; j++ {
			if matrix[currentID][j] == 1 {
				result = currentID
				break
			}
		}
	}
	defer wg.Done()
	fmt.Println("routine finished")
}
