package main

import (
	"math/rand"
	_ "math/rand"
	"sync"
)

var waitGroup sync.WaitGroup
var matrix [5][5]int
var m sync.RWMutex

func main() {
	for i := 0; i < 14; i++ {
		x := rand.Intn(5)
		y := rand.Intn(5)
		num := rand.Intn(10)
		matrix[x][y] = num
		matrix[y][x] = num
	}
	for i := 0; i < 5; i++ {
		for j := 0; j < 5; j++ {
			print(matrix[i][j])
		}
		print("\n")
	}
	//waitGroup.Add(1)

}
