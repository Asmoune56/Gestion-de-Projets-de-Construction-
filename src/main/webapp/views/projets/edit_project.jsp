<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Edit Project</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f4f4f4;
      margin: 0;
      padding: 0;
    }
    .container {
      width: 50%;
      margin: 50px auto;
      background-color: #fff;
      padding: 20px;
      border-radius: 5px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }
    h1 {
      text-align: center;
      color: #333;
    }
    form {
      display: flex;
      flex-direction: column;
    }
    label {
      margin: 10px 0 5px;
      font-size: 14px;
    }
    input, textarea {
      padding: 10px;
      margin-bottom: 20px;
      font-size: 14px;
      border: 1px solid #ccc;
      border-radius: 5px;
    }
    input[type="submit"] {
      background-color: #4CAF50;
      color: white;
      border: none;
      cursor: pointer;
    }
    input[type="submit"]:hover {
      background-color: #45a049;
    }
    .back-button {
      background-color: #f39c12;
      color: white;
      padding: 10px 20px;
      text-decoration: none;
      text-align: center;
      border-radius: 5px;
      display: inline-block;
      margin-top: 20px;
    }
    .back-button:hover {
      background-color: #e67e22;
    }
  </style>
</head>
<body>

<div class="container">
  <h1>Edit Project</h1>

  <form action="${pageContext.request.contextPath}/projects/edit" method="POST">
    <input type="hidden" name="id" value="${not empty project ? project.id : ''}">

    <label for="name">Project Name:</label>
    <input type="text" id="name" name="name" value="${not empty project ? project.nom : ''}" required>

    <label for="description">Project Description:</label>
    <textarea id="description" name="description" rows="4" required>${not empty project ? project.description : ''}</textarea>

    <label for="startDate">Start Date:</label>
    <input type="date" id="startDate" name="startDate" value="${not empty project ? project.dateDebut : ''}" required>

    <label for="endDate">End Date:</label>
    <input type="date" id="endDate" name="endDate" value="${not empty project ? project.dateFin : ''}" required>

    <input type="submit" value="Update Project">
  </form>


  <a href="${pageContext.request.contextPath}/projects/list" class="back-button">Back to Project List</a>
</div>

</body>
</html>
