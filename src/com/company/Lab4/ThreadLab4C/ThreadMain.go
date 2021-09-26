package main

import (
	"fmt"
	_ "math/rand"
	"os"
	"sync"
)

var waitGroup sync.WaitGroup

func main() {
	//var matrix [20][20]int

	//waitGroup.Add(1)
	file, err := os.OpenFile("Graph.txt", os.O_RDONLY, 0666)
	if err != nil {
		fmt.Println("Unable to create file:", err)
		os.Exit(1)
		defer file.Close()
	}
	//waitGroup.Wait()
}
