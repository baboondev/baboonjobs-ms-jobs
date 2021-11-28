# Flow and endpoints

**Create Job:**

1. POST /jobs
2. Validate jwt (role === Employeer)
3. Validate: dateToWork > today + 5
4. Validate fields
5. Return

**Get Jobs **

	1. GET /jobs
	2. Query
	 	1. groupJob
	 	2. authorId
	 	3. available (bool) -> acceptedJob == null?
	 	4. limit
	 	5. offset

**Get Job**

1. GET /jobs/{id}

**Add Offer**

1. POST /offers/{jobId}
2. Validate role
3. Validate Job
4. Validate fields
5. update Job(id) --> add offer in Offers
6. return 201 (created)

**Accept Job**

1. PATCH /jobs/{id}
2. Body: {Offer}
3. Validate user author (Job.author == user.Id)
4. Validate "offer"
5. set acceptedOffer: offer
6. Send mail:
   1. get ms-users/users/{offer.authorId} [httpClient]
   2. sendMail(worker.email, subject, body)



