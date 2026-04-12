import random
import string


class DataGenerator:

    ROBOT_LIBRARY_SCOPE = 'TEST SUITE'

    def generate_random_employee_data(self):
        """
        Generates a dictionary with random employee details to avoid 500 errors.
        """
        # Create a random 5-character string (e.g., 'a1b2c')
        suffix = ''.join(random.choices(string.ascii_lowercase + string.digits, k=5))

        first_names = ["Arenaho", "Juska", "Uhone", "Muhali", "Alpha"]
        last_names = ["Dev", "Tester", "Solutions", "Creative", "User"]

        f_name = random.choice(first_names)
        l_name = random.choice(last_names)

        # Generate a unique email every time
        email = f"{f_name.lower()}.{suffix}@muhali.co.za"

        # Standard password format
        password = f"Pass_{suffix}!"

        return {
            "firstName": f_name,
            "lastName": l_name,
            "email": email,
            "password": password
        }