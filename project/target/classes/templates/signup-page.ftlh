<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign Up</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.2/dist/morph/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(to right, #008DDA, #41C9E2, #ACE2E1);
            height: 100vh;
            margin: 0;
            display: flex;
            justify-content: center;
            align-items: center;
        }
    </style>
</head>

<body>
<div class="container-lg mt-8">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div>
                <div>
                    <h4 class="mb-0">Goal Forge - Sign Up</h4>
                </div>
                <div>
                    <form action="/signup" method="post">
                        <div id="errorMessage" class="alert alert-danger" style="display: none;">
                            User with this email already exists!
                        </div>
                        <div class="mb-3">
                            <label for="firstName" class="form-label">First Name</label>
                            <input type="text" class="form-control" id="firstName" name="firstName" required>
                        </div>
                        <div class="mb-3">
                            <label for="lastName" class="form-label">Last Name</label>
                            <input type="text" class="form-control" id="lastName" name="lastName" required>
                        </div>
                        <div class="mb-3">
                            <label for="email" class="form-label">Email Address</label>
                            <input type="email" class="form-control" id="email" name="email" required>
                        </div>
                        <div class="mb-3">
                            <label for="password" class="form-label">Password</label>
                            <div class="input-group">
                                <input type="password" class="form-control" id="password" name="password"
                                       required>
                                <button class="btn btn-outline-secondary" type="button" id="showPasswordBtn">Show
                                    Password</button>
                            </div>
                        </div>
                        <div class="mb-3">
                            <label for="confirmPassword" class="form-label">Confirm Password</label>
                            <input type="password" class="form-control" id="confirmPassword" name="confirmPassword"
                                   required>
                        </div>
                        <div class="mb-3 form-check">
                            <input type="checkbox" class="form-check-input" id="terms" name="terms" required>
                            <label class="form-check-label" for="terms">I agree to the terms and conditions</label>
                        </div>
                        <button type="submit" class="btn btn-dark">Sign Up</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    // Check if the error query parameter exists in the URL and display the error message if needed
    const urlParams = new URLSearchParams(window.location.search);
    const error = urlParams.get('error');
    if (error === 'exists') {
        document.getElementById('errorMessage').style.display = 'block';
    }
    const passwordInput = document.getElementById('password');
    const showPasswordBtn = document.getElementById('showPasswordBtn');

    showPasswordBtn.addEventListener('click', () => {
        if (passwordInput.type === 'password') {
            passwordInput.type = 'text';
            showPasswordBtn.textContent = 'Hide Password';
        } else {
            passwordInput.type = 'password';
            showPasswordBtn.textContent = 'Show Password';
        }
    });
</script>
</body>

</html>
