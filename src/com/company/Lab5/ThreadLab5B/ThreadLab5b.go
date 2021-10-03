package main

import (
	"sync"
	"time"
)

var waitGroup sync.WaitGroup
var barrier sync.WaitGroup
var flag bool

func main() {
	array1 := []string{"A", "B", "C", "D", "B", "C", "A", "C", "D", "B"}
	array2 := []string{"B", "C", "D", "A", "B", "D", "B", "A", "C", "D"}
	array3 := []string{"B", "C", "B", "A", "A", "D", "B", "D", "A", "B"}
	array4 := []string{"B", "C", "B", "A", "A", "D", "D", "B", "D", "B"}
	chanelA := make(chan int)
	chanelB := make(chan int)

	waitGroup.Add(4)
	go mainFunc(array1, chanelA, chanelB)
	go mainFunc(array2, chanelA, chanelB)
	go mainFunc(array3, chanelA, chanelB)
	go mainFunc(array4, chanelA, chanelB)

	waitGroup.Wait()

}
func countA(array []string, chanelA chan int) {
	countA := 0
	for i := 0; i < len(array); i++ {
		if array[i] == "A" {
			countA++
		}
	}
	chanelA <- countA
}
func countB(array []string, chanelB chan int) {
	countB := 0
	for i := 0; i < len(array); i++ {
		if array[i] == "B" {
			countB++
		}
	}
	chanelB <- countB
}
func compare(chanel chan int) {
	count1, count2, count3, count4 := <-chanel, <-chanel, <-chanel, <-chanel
	if (count1 == count2 && count1 == count3) || (count1 == count2 && count1 == count4) ||
		(count1 == count3 && count1 == count4) || (count2 == count3 && count2 == count4) {
		flag = true
	}
	flag = false
}
func mainFunc(array []string, chanelA chan int, chanelB chan int) {
	countA(array, chanelA)
	countB(array, chanelB)
	time.Sleep(5000)
	waitGroup.Done()
}

func compareResult(chanelA chan int, chanelB chan int) {
	compare(chanelA)
	compare(chanelB)
	if flag == true {
	}
}
