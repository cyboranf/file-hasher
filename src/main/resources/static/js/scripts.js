document.getElementById("upload-form").addEventListener("submit", function (event) {
    event.preventDefault();

    let fileInput = document.getElementById("file");
    let algorithmSelect = document.querySelector("select[name='algorithm']");
    let formData = new FormData();
    formData.append("file", fileInput.files[0]);
    formData.append("algorithm", algorithmSelect.value);

    fetch('/upload', {
        method: 'POST',
        body: formData
    }).then(response => response.text())
        .then(result => {
            document.getElementById("result").innerText = result;
        }).catch(error => {
        document.getElementById("result").innerText = "Error: " + error;
    });
});
