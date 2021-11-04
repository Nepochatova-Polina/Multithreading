package main

import (
	"math/rand"
	"sync"
	"time"
)

var barrier sync.WaitGroup

func main() {
	bid1 := make(chan int, 4)
	bid2 := make(chan int, 4)
	bid3 := make(chan int, 4)
	bid4 := make(chan int, 4)
	for i := 0; i < rand.Intn(10); i++ {
		barrier.Add(4)
		go newThread(bid1, &barrier)
		go newThread(bid2, &barrier)
		go newThread(bid3, &barrier)
		go newThread(bid4, &barrier)
		barrier.Wait()
		compareBids(bid1, bid2, bid3, bid4)
	}

	close(bid1)
	close(bid2)
	close(bid3)
	close(bid4)
}

func makeBids(bids chan int) {
	bids <- rand.Intn(50)
}
func changeBid(bids chan int) {
	x := <-bids
	bids <- x + 3
}
func newThread(bid chan int, wg *sync.WaitGroup) {
	makeBids(bid)
	for i := 0; i < 3; i++ {
		changeBid(bid)
	}
	wg.Done()

}
func compareBids(bid1 chan int, bid2 chan int, bid3 chan int, bid4 chan int) {
	count1, count2, count3, count4 := <-bid1, <-bid2, <-bid3, <-bid4
	if count1 > count2 && count1 > count3 && count1 > count4 {
		print("Bid #1` Win! " + "price: ")
		print(count1)
		print("\n")
		PayBid(rand.Intn(2))
	} else if count2 > count1 && count2 > count3 && count2 > count4 {
		print("Bid #2` Win! " + "price: ")
		print(count2)
		print("\n")
		PayBid(rand.Intn(2))
	} else if count3 > count1 && count3 > count2 && count3 > count4 {
		print("Bid #3` Win! " + "price: ")
		print(count3)
		print("\n")
		PayBid(rand.Intn(2))
	} else {
		print("Bid #4` Win! " + "price: ")
		print(count4)
		print("\n")
		PayBid(rand.Intn(2))
	}

}
func PayBid(x int) {
	time.Sleep(3 * time.Second)
	if x == 1 {
		print("Bid paid\n")
	} else {
		print("bid ignored\n")
	}
}
