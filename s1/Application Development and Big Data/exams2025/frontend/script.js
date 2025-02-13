function validateInput(input) {
    // Επιτρέπει μόνο έως 2 δεκαδικά και μόνο ένα πόντο (.)
    let value = input.value.replace(/[^0-9.-]/g, '').replace(/(\..*?)\..*/g, '$1');
    if (value.includes('-')) {
        value = value.startsWith('-') ? value.replace(/-/g, '-') : value.replace('-', '');
    }
    const parts = value.split('.');
    if (parts.length > 1 && parts[1].length > 2) {
        parts[1] = parts[1].substring(0, 2);
        value = parts.join('.');
    }
    input.value = value;
}

function updateEquation() {
    const number1 = parseFloat(document.getElementById('number1').value) || 0;
    const number2 = parseFloat(document.getElementById('number2').value) || 0;
    const number3 = parseFloat(document.getElementById('number3').value) || 0;
    const number4 = parseFloat(document.getElementById('number4').value) || 0;

    let equation = '';

    if (number1 !== 0) {
        equation += `${formatNumber(number1)}X²`;
    }

    if (number2 !== 0) {
        if (equation !== '') {
            equation += ` ${number2 > 0 ? '+' : '-'} `;
        }
        if (Math.abs(number2) !== 1) {
            equation += `${formatNumber(Math.abs(number2))}`;
        }
        equation += 'X';
    }

    if (number3 !== 0) {
        if (equation !== '') {
            equation += ` ${number3 > 0 ? '+' : '-'} `;
        }
        equation += `${formatNumber(Math.abs(number3))}`;
    }

    equation += ' = ';
    if (number4 !== 0) {
        equation += `${formatNumber(number4)}`;
    } else {
        equation += '0';
    }

    document.getElementById('equation').innerText = equation;
}

function formatNumber(num) {
    return num.toFixed(2).replace(/\.?0+$/, ''); // Αφαίρεση μηδενικών μετά το πόντο
}
