# Data models (Schemas)

## Job
```javascript
Job {
    _id: ObjectId,
    authorId: ObjectId,
    groupJob: string,
    dateToWork: Date,
    location: {
    	address: string,
        city: string,
        country: string
    },
    description: string
    offers: [Offer]
    acceptedOffer: Offer
    createdAd: Date
}
```

## Offer
```javascript
Offer {
	_id: ObjectId,
	jobId: ObjectId
	authorId: ObjectId,
	price: Double,
	description: string,
	createdAt: Date
}
```