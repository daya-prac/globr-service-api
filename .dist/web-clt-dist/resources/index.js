var size = 50,
    u = 'px',
    color1 = '#a0d633',
    color2 = '#50ba80',
    color3 = '#c07',
    color4 = '#fff',
    grad2 = 'url(\'data:image/svg+xml;utf8,<svg width="100" height="100" xmlns="http://www.w3.org/2000/svg"><rect x="0" y="0" width="50" height="50" fill="' + color2 + '"/><rect x="50" y="50" width="50" height="50" fill="' + color2 + '"/></svg>\')',
    grad1 = 'url(\'data:image/svg+xml;utf8,<svg width="400" height="400" xmlns="http://www.w3.org/2000/svg">*</svg>\')';

var circle = function (cx, cy, color) {
    return [
    '<circle cx="' + cx + '" cy="' + cy + '" r="3" fill="' + color + '"/>',
    '<circle cx="' + (cx + 5) + '" cy="' + cy + '" r="2.5" fill="' + color + '"/>',
    '<circle cx="' + (cx - 5) + '" cy="' + cy + '" r="2.5" fill="' + color + '"/>',
    '<circle cx="' + cx + '" cy="' + (cy + 5) + '" r="2.5" fill="' + color + '"/>',
    '<circle cx="' + cx + '" cy="' + (cy - 5) + '" r="2.5" fill="' + color + '"/>',
    '<circle cx="' + (cx + 9) + '" cy="' + cy + '" r="1.5" fill="' + color + '"/>',
    '<circle cx="' + (cx - 9) + '" cy="' + cy + '" r="1.5" fill="' + color + '"/>',
    '<circle cx="' + cx + '" cy="' + (cy + 9) + '" r="1.5" fill="' + color + '"/>',
    '<circle cx="' + cx + '" cy="' + (cy - 9) + '" r="1.5" fill="' + color + '"/>',
  ].join('');
}

var circles = '';

for (var x = 0; x <= 8; x++) {
    for (var y = 0; y <= 8; y++) {
        var n = x - y,
            color = color3;
        if (n < 0) n += 8;
        if (n == 2 || n == 4 || n == 5 || n == 7) {
            color = color4;
        }
        circles += circle(x * size, y * size, color);
    }
}

grad1 = grad1.replace('*', circles);


document.body.style['background-size'] = size * 8 + u + ' ' + size * 8 + u + ',' + size * 2 + u + ' ' + size * 2 + u;
document.body.style['background-color'] = color1;
document.body.style['background-image'] = grad1 + ',' + grad2;

/*
感谢codepen的作者:Sandra Robotos和Report Abuse
本站部分页面背景是修改于他们的源代码
*/
