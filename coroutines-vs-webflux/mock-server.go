package main

import (
	"encoding/json"
	"log"
	"net/http"
	"time"
)

type Data struct {
	Message string
}

func handler(w http.ResponseWriter, r *http.Request) {
	// time.Sleep(time.Duration(rand.Int31n(500)) * time.Millisecond)
	time.Sleep(time.Duration(100 * time.Millisecond))

	titles, ok := r.URL.Query()["title"]

	if !ok || len(titles[0]) < 1 {
		log.Println("Url Param 'title' is missing")
		return
	}

	title := titles[0]

	w.Header().Set("Content-Type", "application/json")
	data := Data{"from go server: " + title}
	json.NewEncoder(w).Encode(data)
}

func main() {
	http.HandleFunc("/", handler)
	log.Fatal(http.ListenAndServe(":8085", nil))
}

// go run http-servers.go

// $ curl localhost:8085/hello

// for i in {1..5}; do time curl localhost:8085 &; done
