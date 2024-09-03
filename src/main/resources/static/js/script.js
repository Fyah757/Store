// Example of simple client-side validation using JavaScript (optional)
document.addEventListener('DOMContentLoaded', function () {
    const form = document.querySelector('form');
    form.addEventListener('submit', function (event) {
        const email = document.getElementById('email');
        const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailPattern.test(email.value)) {
            alert('Please enter a valid email address.');
            event.preventDefault(); // Prevent form submission
        }
    });
});
