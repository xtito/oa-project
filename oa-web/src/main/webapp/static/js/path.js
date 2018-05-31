var projectName = function () {
    var pathName = document.location.pathname;
    console.info(pathName);
    var index = pathName.substr(1).indexOf("/");
    console.info(index);
    return pathName.substr(0, index + 1);
};