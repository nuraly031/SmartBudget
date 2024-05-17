document.addEventListener('DOMContentLoaded', () => {
    const assetNameInput = document.getElementById('assetName');
    const assetValueInput = document.getElementById('assetValue');
    const addAssetButton = document.getElementById('addAssetButton');
    const assetList = document.getElementById('assetList');
    const totalValueSpan = document.getElementById('totalValue');

    let assets = [];

    // Загрузка существующих активов с сервера
    fetch('/assets')
        .then(response => response.json())
        .then(data => {
            assets = data.assets;
            renderAssets();
            updateTotal();
        });

    addAssetButton.addEventListener('click', () => {
        const name = assetNameInput.value;
        const value = parseFloat(assetValueInput.value);

        if (name && value) {
            const asset = { name, value };

            // Отправка нового актива на сервер
            fetch('/assets', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(asset)
            })
            .then(() => {
                assets.push(asset);
                renderAssets();
                updateTotal();
            });

            assetNameInput.value = '';
            assetValueInput.value = '';
        }
    });

    function renderAssets() {
        assetList.innerHTML = '';
        assets.forEach((asset) => {
            const li = document.createElement('li');
            li.textContent = `${asset.name} - $${asset.value}`;
            assetList.appendChild(li);
        });
    }

    function updateTotal() {
        const total = assets.reduce((sum, asset) => sum + asset.value, 0);
        totalValueSpan.textContent = `$${total}`;
    }
});
