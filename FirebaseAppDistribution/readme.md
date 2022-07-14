## Firebase App Distribution
1. [Add firebase into your project](https://firebase.google.com/docs/android/setup)
2. [Install Firebase CLI](https://firebase.google.com/docs/cli)
3. To execute graldew to build apk
4. Run Firebase CLI to upload apk to firebase.
5. If success, you can find the build in App Distribution section.

## Run the script to upload apk to firebase to tester
- [Firebase CLI more details](https://firebase.google.com/docs/app-distribution/android/distribute-cli)
```
firebase appdistribution:distribute test.apk  \
    --app 1:1234567890:android:0a1b2c3d4e5f67890  \
    --release-notes "Bug fixes and improvements"
```