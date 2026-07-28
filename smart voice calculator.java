<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Smart Voice Calculator</title>
<style>
  :root {
    --bg: #0f1220;
    --panel: #1a1f38;
    --accent: #6c63ff;
    --accent-2: #22d3ee;
    --text: #e8e9f3;
    --muted: #8b8fb3;
  }
  * { box-sizing: border-box; }
  body {
    margin: 0;
    min-height: 100vh;
    display: flex;
    align-items: center;
    justify-content: center;
    background: radial-gradient(circle at top, #1c2140, #0a0c18);
    font-family: 'Segoe UI', Roboto, sans-serif;
    color: var(--text);
    padding: 20px;
  }
  .app {
    width: 100%;
    max-width: 420px;
    background: var(--panel);
    border-radius: 20px;
    padding: 24px;
    box-shadow: 0 20px 50px rgba(0,0,0,0.4);
  }
  h1 {
    font-size: 20px;
    text-align: center;
    margin: 0 0 4px;
  }
  .subtitle {
    text-align: center;
    color: var(--muted);
    font-size: 13px;
    margin-bottom: 18px;
  }
  .display {
    background: #0d0f1e;
    border-radius: 14px;
    padding: 18px;
    text-align: right;
    margin-bottom: 16px;
    min-height: 74px;
  }
  #expression {
    color: var(--muted);
    font-size: 14px;
    min-height: 18px;
    word-wrap: break-word;
  }
  #result {
    font-size: 32px;
    font-weight: 600;
    word-wrap: break-word;
  }
  .mic-row {
    display: flex;
    justify-content: center;
    margin-bottom: 16px;
  }
  #micBtn {
    width: 64px;
    height: 64px;
    border-radius: 50%;
    border: none;
    background: linear-gradient(135deg, var(--accent), var(--accent-2));
    color: white;
    font-size: 26px;
    cursor: pointer;
    transition: transform .15s ease, box-shadow .15s ease;
    box-shadow: 0 6px 18px rgba(108,99,255,0.4);
  }
  #micBtn:hover { transform: scale(1.05); }
  #micBtn.listening {
    animation: pulse 1s infinite;
  }
  @keyframes pulse {
    0% { box-shadow: 0 0 0 0 rgba(108,99,255,0.6); }
    70% { box-shadow: 0 0 0 16px rgba(108,99,255,0); }
    100% { box-shadow: 0 0 0 0 rgba(108,99,255,0); }
  }
  #micStatus {
    text-align: center;
    font-size: 12px;
    color: var(--muted);
    margin-bottom: 16px;
    min-height: 16px;
  }
  .keypad {
    display: grid;
    grid-template-columns: repeat(4, 1fr);
    gap: 8px;
    margin-bottom: 16px;
  }
  .keypad button {
    padding: 14px 0;
    border: none;
    border-radius: 10px;
    background: #232748;
    color: var(--text);
    font-size: 16px;
    cursor: pointer;
    transition: background .15s ease;
  }
  .keypad button:hover { background: #2c315a; }
  .keypad button.op { background: #33306a; color: var(--accent-2); }
  .keypad button.equals {
    background: linear-gradient(135deg, var(--accent), var(--accent-2));
    color: #fff;
  }
  .keypad button.clear { background: #4a2340; color: #ff8fa3; }
  .history {
    max-height: 160px;
    overflow-y: auto;
    border-top: 1px solid #2a2f52;
    padding-top: 10px;
  }
  .history h2 {
    font-size: 13px;
    color: var(--muted);
    margin: 0 0 8px;
    font-weight: 500;
  }
  .history-item {
    display: flex;
    justify-content: space-between;
    font-size: 13px;
    padding: 6px 0;
    color: var(--muted);
    border-bottom: 1px dashed #262b4c;
    cursor: pointer;
  }
  .history-item span.res { color: var(--text); font-weight: 600; }
  #serverUrlRow {
    margin-top: 14px;
    font-size: 11px;
    color: var(--muted);
    text-align: center;
  }
  #serverUrlRow input {
    background: #0d0f1e;
    border: 1px solid #2a2f52;
    border-radius: 6px;
    color: var(--text);
    padding: 4px 8px;
    font-size: 11px;
    width: 190px;
  }
</style>
</head>
<body>

<div class="app">
  <h1>🎙️ Smart Voice Calculator</h1>
  <div class="subtitle">Speak or tap to calculate</div>

  <div class="display">
    <div id="expression">0</div>
    <div id="result">0</div>
  </div>

  <div class="mic-row">
    <button id="micBtn" title="Tap and speak, e.g. 'twelve plus five'">🎤</button>
  </div>
  <div id="micStatus">Tap the mic and say something like "12 plus 5"</div>

  <div class="keypad">
    <button onclick="press('7')">7</button>
    <button onclick="press('8')">8</button>
    <button onclick="press('9')">9</button>
    <button class="op" onclick="press('/')">÷</button>

    <button onclick="press('4')">4</button>
    <button onclick="press('5')">5</button>
    <button onclick="press('6')">6</button>
    <button class="op" onclick="press('*')">×</button>

    <button onclick="press('1')">1</button>
    <button onclick="press('2')">2</button>
    <button onclick="press('3')">3</button>
    <button class="op" onclick="press('-')">−</button>

    <button onclick="press('0')">0</button>
    <button onclick="press('.')">.</button>
    <button class="clear" onclick="clearAll()">C</button>
    <button class="op" onclick="press('+')">+</button>

    <button class="equals" style="grid-column: span 4;" onclick="calculate()">=</button>
  </div>

  <div class="history">
    <h2>Recent history</h2>
    <div id="historyList"></div>
  </div>

  <div id="serverUrlRow">
    Backend API:
    <input id="serverUrl" value="http://localhost:8080" />
  </div>
</div>

<script>
  let currentExpression = '';
  let localHistory = [];

  function getServerUrl() {
    return document.getElementById('serverUrl').value.replace(/\/$/, '');
  }

  function press(char) {
    if (currentExpression === '0') currentExpression = '';
    currentExpression += char;
    document.getElementById('expression').textContent = currentExpression;
  }

  function clearAll() {
    currentExpression = '';
    document.getElementById('expression').textContent = '0';
    document.getElementById('result').textContent = '0';
  }

  function speakResult(text) {
    if ('speechSynthesis' in window) {
      window.speechSynthesis.cancel();
      const utterance = new SpeechSynthesisUtterance(text);
      window.speechSynthesis.speak(utterance);
    }
  }

  async function calculate(spokenText) {
    const expr = spokenText !== undefined ? spokenText : currentExpression;
    if (!expr || !expr.trim()) return;

    document.getElementById('micStatus').textContent = 'Calculating...';

    // 1. Try Spring Boot Backend first
    try {
      const res = await fetch(getServerUrl() + '/api/calculate', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ expression: expr })
      });
      if (res.ok) {
        const data = await res.json();
        if (data.result !== undefined) {
          currentExpression = data.expression || expr;
          document.getElementById('expression').textContent = currentExpression;
          document.getElementById('result').textContent = data.result;
          document.getElementById('micStatus').textContent = 'Done (MySQL Saved)';
          speakResult('The result is ' + data.result);
          loadHistory();
          return;
        }
      }
    } catch (err) {
      // Backend not running -> fallback to in-browser engine
    }

    // 2. In-Browser Standalone Voice Engine Fallback
    try {
      const mathExpr = parseVoiceToMath(expr);
      const val = evalMath(mathExpr);
      const formattedResult = Number.isInteger(val) ? val.toString() : val.toFixed(4);

      currentExpression = mathExpr;
      document.getElementById('expression').textContent = mathExpr;
      document.getElementById('result').textContent = formattedResult;
      document.getElementById('micStatus').textContent = 'Done';
      speakResult('The result is ' + formattedResult);

      addLocalHistory(mathExpr, formattedResult);
    } catch (err) {
      document.getElementById('result').textContent = 'Error';
      document.getElementById('micStatus').textContent = 'Could not calculate expression';
    }
  }

  function parseVoiceToMath(input) {
    let clean = input.toLowerCase().trim();
    clean = clean.replace(/^(what is|calculate|compute|solve|tell me)\s+/g, '').replace(/\?/g, '');
    
    const words = { 'zero':'0','one':'1','two':'2','three':'3','four':'4','five':'5','six':'6','seven':'7','eight':'8','nine':'9','ten':'10','eleven':'11','twelve':'12','thirteen':'13','fourteen':'14','fifteen':'15','sixteen':'16','seventeen':'17','eighteen':'18','nineteen':'19','twenty':'20','thirty':'30','forty':'40','fifty':'50','sixty':'60','seventy':'70','eighty':'80','ninety':'90','hundred':'100','thousand':'1000' };
    
    clean.split(/\s+/).forEach(w => {
      if (words[w]) clean = clean.replace(new RegExp('\\b' + w + '\\b', 'g'), words[w]);
    });

    clean = clean.replace(/plus|add|added to/g, '+')
                 .replace(/minus|subtract|take away/g, '-')
                 .replace(/multiplied by|times|into|x/g, '*')
                 .replace(/divided by|over|slash/g, '/')
                 .replace(/square root of|sqrt of|square root/g, 'Math.sqrt')
                 .replace(/power of|raised to|\^/g, '**');

    return clean;
  }

  function evalMath(expr) {
    if (expr.includes('Math.sqrt')) {
      const match = expr.match(/Math\.sqrt\s*\(?(\d+(?:\.\d+)?)\)?/);
      if (match) return Math.sqrt(parseFloat(match[1]));
    }
    const sanitized = expr.replace(/[^0-9\+\-\*\/\.\(\)\s\*\*\s]/g, '');
    return Function('"use strict";return (' + sanitized + ')')();
  }

  function addLocalHistory(expr, res) {
    localHistory.unshift({ expression: expr, result: res });
    renderHistory(localHistory);
  }

  function renderHistory(items) {
    const list = document.getElementById('historyList');
    list.innerHTML = '';
    items.slice(0, 8).forEach(item => {
      const row = document.createElement('div');
      row.className = 'history-item';
      row.innerHTML = `<span>${item.expression}</span><span class="res">= ${item.result}</span>`;
      row.onclick = () => {
        currentExpression = item.expression;
        document.getElementById('expression').textContent = item.expression;
        document.getElementById('result').textContent = item.result;
      };
      list.appendChild(row);
    });
  }

  async function loadHistory() {
    try {
      const res = await fetch(getServerUrl() + '/api/history');
      if (res.ok) {
        const items = await res.json();
        renderHistory(items);
      } else {
        renderHistory(localHistory);
      }
    } catch (err) {
      renderHistory(localHistory);
    }
  }

  // --- Voice recognition ---
  const SpeechRecognition = window.SpeechRecognition || window.webkitSpeechRecognition;
  const micBtn = document.getElementById('micBtn');
  const micStatus = document.getElementById('micStatus');

  if (!SpeechRecognition) {
    micStatus.textContent = 'Voice input needs Chrome or Edge. You can still use the keypad.';
    micBtn.disabled = true;
    micBtn.style.opacity = 0.4;
  } else {
    const recognition = new SpeechRecognition();
    recognition.lang = 'en-US';
    recognition.interimResults = false;
    recognition.maxAlternatives = 1;

    micBtn.addEventListener('click', () => {
      micBtn.classList.add('listening');
      micStatus.textContent = 'Listening...';
      recognition.start();
    });

    recognition.onresult = (event) => {
      const transcript = event.results[0][0].transcript;
      micStatus.textContent = 'Heard: "' + transcript + '"';
      calculate(transcript);
    };

    recognition.onerror = (event) => {
      micStatus.textContent = 'Voice error: ' + event.error;
    };

    recognition.onend = () => {
      micBtn.classList.remove('listening');
    };
  }

  // Initial load
  loadHistory();
</script>

</body>
</html>
