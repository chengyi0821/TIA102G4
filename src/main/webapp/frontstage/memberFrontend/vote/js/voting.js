/**
 * 
 */
document.addEventListener("DOMContentLoaded", function () {
		const options = document.querySelectorAll(".option");
		const submitButton = document.getElementById("submitButton");

		options.forEach((option) => {
			option.addEventListener("click", function () {
				const radio = this.querySelector('input[type="radio"]');

				if (radio.checked) {
					radio.checked = false;
					this.classList.remove("selected");
					submitButton.style.display = "none";
				} else {
					options.forEach((opt) => {
						opt.classList.remove("selected");
						opt.querySelector('input[type="radio"]').checked = false;
					});

					radio.checked = true;
					this.classList.add("selected");
					submitButton.style.display = "block";
				}
			});
		});

		submitButton.addEventListener("click", function () {
			const form = document.querySelector("form");
			if (form) {
				form.submit();
			}
		});
	});