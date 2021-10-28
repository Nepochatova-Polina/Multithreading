package main

import (
	"fmt"
	"math/rand"
	"sync"
	"time"
)

var waitGroup sync.WaitGroup
var cellID int = 0
var potIsFull bool = false

func main() {
	array := make(chan int, 50)
	waitGroup.Add(1)
	for i := 0; i < 4; i++ {
		waitGroup.Add(1)
		go fillThePot(array)
	}
	go bearIsComing()

	waitGroup.Wait()
}

func fillThePot(array chan int) {
	var mutex sync.Mutex
	for cellID >= 0 {
		incrementCellID(&mutex)
		if cellID < 49 {
			fmt.Println("Thread is working with the cell #:", cellID)
			array <- rand.Intn(100)
		} else {
			potIsFull = true
			break
		}
	}
	waitGroup.Done()
}

func bearIsComing() {
	for potIsFull == false {
		time.Sleep(2 * time.Second)
	}
	fmt.Println("The pot was devastated by bear")
	waitGroup.Done()
}

func incrementCellID(mutex *sync.Mutex) int {
	mutex.Lock()
	cellID++
	mutex.Unlock()
	return cellID
}
