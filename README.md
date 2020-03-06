# OrmucoTest

## Question C

### 1. Simplicity:
By packaging my codes into a **Gradle/Maven** project, we get a new library that is easy to integrate.

### 2. Resilient to network failures and crashes:
Since the caches are stored locally, when the network failures appear, we can still get values from the cache. Only the cache gets disconnected from the database.

### 3. Near real time replication of data across Geolocation:
I do not quite know how to realize this, but, in my opinion, the cache should not be changed frequently. Hence, it will realize a real time replication to some extent.

### 4. Data Consistency:
The cache will get all the data from the remote database, so the value in cache will be consistent.

### 5. Locality of reference:
Connecting cache to servers(databases) in different locations will realize locality of reference.

### 6. Flexible Schema:
I prvided **write, get, and remove** functions in the library, which help manipulate the cache.

### 7. Cache can expire:
I provided an expiration check in **get** function. It won't keep checking continuously and delete the value instantly after it expires.
If we wanted to check expiration continuously, we would have to create a new thread. This will make the codes way too complicated (break simplicity), and the codes will become really brittle.
