
function onStorageEvent(storageEvent){
    console.log(storageEvent.key);
    console.log(storageEvent.newValue);
    storageEventListener(storageEvent.key, storageEvent.newValue);
}

window.addEventListener('storage', onStorageEvent, false);

