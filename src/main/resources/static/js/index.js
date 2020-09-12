window.onload = function () {
    if (window.localStorage.getItem("closeable") === "true") {
        window.close();
        window.localStorage.removeItem("closeable");
    }
};