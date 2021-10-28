package main

import (
	"fmt"
	"io/ioutil"
	"math/rand"
	_ "math/rand"
	"os"
	"strconv"
	"sync"
	"time"
)

var waitGroup sync.WaitGroup
var matrix [5][5]int
var m sync.RWMutex

func main() {
	waitGroup.Add(4)

	for i := 0; i < 5; i++ {
		for j := 0; j < 5; j++ {
			matrix[i][j] = rand.Intn(2)
		}
	}
	go Gardener()
	go Nature()
	go WriteToCOnsole()
	go WriteIntoFile()
	waitGroup.Wait()

}
func Nature() {
	for {
		m.Lock()
		print("Nature is working...\n")
		for i := 0; i < 10; i++ {
			matrix[rand.Intn(5)][rand.Intn(5)] = 0
		}
		print("Nature stops\n")
		m.Unlock()
		time.Sleep(2 * time.Second)
	}
}
func Gardener() {
	for {
		m.Lock()
		print("Gardener is watering plants...\n")
		for i := 0; i < 10; i++ {
			x := rand.Intn(5)
			y := rand.Intn(5)
			if matrix[x][y] == 0 {
				matrix[x][y] = 1
			}
		}
		print("Gardener finished \n")
		m.Unlock()
		time.Sleep(2 * time.Second)
	}
}
func WriteToCOnsole() {
	for {
		m.RLock()
		print("Writing matrix \n")
		for i := 0; i < 5; i++ {
			for j := 0; j < 5; j++ {
				matrix[i][j] = rand.Intn(2)
				print(matrix[i][j])
			}
			print("\n")
		}
		m.RUnlock()
		time.Sleep(4 * time.Second)
	}
}

func WriteIntoFile() {
	for {
		m.Lock()
		print("Updating file...\n")
		var x string
		file, err := os.Open("GoGarden.txt")
		if err != nil {
			fmt.Println("Unable to create file:", err)
			os.Exit(1)
		}
		defer file.Close()
		for i := 0; i < 5; i++ {
			for j := 0; j < 5; j++ {
				x += strconv.Itoa(matrix[i][j])
			}
			x += " "
		}
		data := []byte(x)
		err = ioutil.WriteFile("GoGarden.txt", data, 0)
		print("Work Done!\n")
		m.Unlock()
		time.Sleep(5 * time.Second)
	}
}
