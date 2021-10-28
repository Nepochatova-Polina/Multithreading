package main

import (
	"math/rand"
	"sync"
)

var waitGroup sync.WaitGroup
var mainFlag bool

func main() {
	array1 := []string{"A", "B", "C", "D", "B", "C", "A", "C", "D", "B"}
	array2 := []string{"B", "C", "D", "A", "B", "D", "B", "A", "C", "D"}
	array3 := []string{"B", "C", "B", "A", "A", "D", "B", "D", "B", "B"}
	array4 := []string{"B", "C", "B", "A", "A", "D", "D", "B", "D", "C"}
	chanelA := make(chan int, 4)
	chanelB := make(chan int, 4)
	waitGroup.Add(1)

	for mainFlag == false {
		var wG sync.WaitGroup
		wG.Add(4)
		go mainFunc(array1, chanelA, chanelB, &wG)
		go mainFunc(array2, chanelA, chanelB, &wG)
		go mainFunc(array3, chanelA, chanelB, &wG)
		go mainFunc(array4, chanelA, chanelB, &wG)
		wG.Wait()
		if compareResult(chanelA, chanelB) == false {
			for len(chanelA) > 0 {
				<-chanelA
				<-chanelB
			}
			wG.Add(4)
			go changeArray(array1, &wG)
			go changeArray(array2, &wG)
			go changeArray(array3, &wG)
			go changeArray(array4, &wG)
			wG.Wait()
		}
	}
	waitGroup.Wait()
	close(chanelA)
	close(chanelB)
}
func mainFunc(array []string, chanelA chan int, chanelB chan int, wG *sync.WaitGroup) {
	var wg sync.WaitGroup
	wg.Add(2)
	go countA(array, chanelA, &wg)
	go countB(array, chanelB, &wg)
	wg.Wait()

	wG.Done()
}

func countA(array []string, chanelA chan int, wg *sync.WaitGroup) {
	countA := 0
	for i := 0; i < len(array); i++ {
		if array[i] == "A" {
			countA++
		}
	}
	chanelA <- countA
	wg.Done()
}
func countB(array []string, chanelB chan int, wg *sync.WaitGroup) {
	countB := 0
	for i := 0; i < len(array); i++ {
		if array[i] == "B" {
			countB++
		}
	}
	chanelB <- countB
	wg.Done()
}
func compare(chanel chan int, flag chan bool) {
	count1, count2, count3, count4 := <-chanel, <-chanel, <-chanel, <-chanel
	if (count1 == count2 && count1 == count3) || (count1 == count2 && count1 == count4) ||
		(count1 == count3 && count1 == count4) || (count2 == count3 && count2 == count4) {
		flag <- true
	}
	flag <- false
}

func compareResult(chanelA chan int, chanelB chan int) bool {
	flagA := make(chan bool)
	flagB := make(chan bool)
	go compare(chanelA, flagA)
	go compare(chanelB, flagB)
	a := <-flagA
	b := <-flagB
	if a != b {
		return false
	} else if a == b && a == false {
		return false
	}
	mainFlag = true
	waitGroup.Done()
	return true
}
func changeArray(array []string, wg *sync.WaitGroup) {
	i := rand.Intn(len(array))
	x := array[i]
	switch x {
	case "A":
		array[i] = "C"
	case "B":
		array[i] = "D"
	case "C":
		array[i] = "A"
	case "D":
		array[i] = "B"
	default:
	}
	wg.Done()

}
