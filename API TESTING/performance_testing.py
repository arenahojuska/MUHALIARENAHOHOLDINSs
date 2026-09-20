from locust import HttpUser, task, between

class PokeApiUser(HttpUser):
    wait_time = between(1, 2)  # Seconds between requests

    @task
    def get_ditto(self):
        self.client.get("/api/v2/pokemon/ditto")
