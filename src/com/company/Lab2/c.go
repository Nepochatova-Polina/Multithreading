package main

import (
	"fmt"
	"math/rand"
)

func s(message string) {
	fmt.Println(message)
}

func main() {

	var a [50][50]int
	for i := 0; i < 50; i++ {
		for j := 0; j < 50; j++ {
			a[i][j] = 0
		}
	}
	a[rand.Intn(50)][rand.Intn(50)] = 1
}
