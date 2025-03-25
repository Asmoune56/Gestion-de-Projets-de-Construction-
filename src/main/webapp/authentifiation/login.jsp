<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: "Poppins", sans-serif;
        }
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background: linear-gradient(rgba(0, 0, 0, 0.7), rgba(0, 0, 0, 0.7)),
            url('https://thumbs.dreamstime.com/b/mur-de-briques-14032537.jpg') no-repeat center center/cover;        }
        .login-container {
            width: 350px;
            padding: 40px;
            background: rgba(255, 255, 255, 0.9);
            border-radius: 10px;
            box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2);
            text-align: center;
        }
        .login-container h2 {
            margin-bottom: 30px;
            color: #333;
            font-weight: 600;
        }
        .input-group {
            margin-bottom: 20px;
            text-align: left;
        }
        .input-group label {
            display: block;
            margin-bottom: 8px;
            color: #555;
            font-size: 14px;
        }
        .input-group input {
            width: 100%;
            padding: 12px 15px;
            background: #f5f5f5;
            border: 1px solid #ddd;
            border-radius: 5px;
            outline: none;
            color: #333;
            font-size: 14px;
        }
        .options {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 25px;
            font-size: 13px;
        }
        .remember-me {
            display: flex;
            align-items: center;
        }
        .remember-me input {
            margin-right: 5px;
        }
        .forgot-password {
            color: #667eea;
            text-decoration: none;
        }
        .forgot-password:hover {
            text-decoration: underline;
        }
        .login-btn {
            width: 100%;
            padding: 12px;
            border: none;
            border-radius: 5px;
            background: #667eea;
            color: white;
            font-size: 16px;
            font-weight: 500;
            cursor: pointer;
            transition: background 0.3s;
        }
        .login-btn:hover {
            background: #5a6fd1;
        }
    </style>
</head>
<body>
<div class="login-container">
    <h2>Login</h2>
    <form action="login" method="post">
        <div class="input-group">
            <label for="username">Username</label>
            <input type="text" id="username" name="username" required>
        </div>
        <div class="input-group">
            <label for="password">Password</label>
            <input type="password" id="password" name="password" required>
        </div>

        <button type="submit" class="login-btn">LOGIN</button>
    </form>
</div>
</body>
</html>