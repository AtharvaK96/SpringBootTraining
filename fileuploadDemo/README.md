Assignments

1. File Upload and Employee Creation:
    - Create a Spring Boot application where users can upload employee profile photos and enter details like name and email.
    - Implement REST endpoints that allow users to upload photos associated with the employee data.
    - Store the file path in the database and save the files on the server.

2. File Validation:
    - Extend the file upload functionality by adding validation (e.g., allow only image files like `.jpg`, `.png`).
    - Implement size restrictions (e.g., allow only files below 5MB).

3. Displaying Uploaded Images:
    - Implement a method in the controller to display the uploaded photo for an employee using their `id`. This should return the photo file stored on the server.
    - Create an API endpoint like `GET /api/employees/{id}/photo` to retrieve and display the employee's photo.

4. Thymeleaf Integration:
    - Extend the project by creating a Thymeleaf-based HTML form for uploading employee photos.
    - After uploading, redirect to a page displaying the employee's details along with the uploaded photo.
