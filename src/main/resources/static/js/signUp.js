function showLoaderFunc() {
    document.getElementById("loader")?.classList?.remove("hidden");
}

function hideLoaderFunc() {
    document.getElementById("loader")?.classList?.add("hidden");
}

document.addEventListener("DOMContentLoaded", () => {
    hideLoaderFunc();
});