# **Anime Studio**

## Table of Contents

1. [App Overview](#App-Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
1. [Build Notes](#Build-Notes)

## Anime Studio Overview


### Description 

**Anime Studio is a streaming app that allows users to watch animes, see their information, and is able to provide a easy to use library of your favorite animes.**

### App Evaluation

<!-- Evaluation of your app across the following attributes -->

- **Category:** Entertainment 
- **Mobile:** This app will be developed for mobile devices, for example smartphones, and tablets, this app will be running on Android.
- **Story:** This apps allows users to discover and watch a wide range of anime
- **Market:** The target audience for this app is anime lovers.
- **Habit:** This app is addictive as it shows new shows easily and you are able to watch your favorite shows fast.
- **Scope:** The scope of this anime is that is will display recyclerviews of anime on the home screen, display relevant information on a show when clicked and stream any episodes/movies when clicked.


## Product Spec

### 1. User Features (Required and Optional)

Required Features:

-[x] **Horizontal Recyclerviews of anime shows (top anime, most watched, shounen, shoujo, isekai, etc..)**
-[x] **Displays relevant information about an anime and its episodes when clicked**
- **Stream episode/movie when clicked with simple video player interface**
- **Able to search animes in the app**

Stretch Features:

- **User can scroll all the way up to reload/generate new suggestions on the home screen**
- **Users can comment on shows**
- **Like/dislike shows/episodes**
- **User can log in to their profile**

### 2. Chosen API(s)

- **https://pkg.go.dev/github.com/crunchy-labs/crunchyroll-go/v3**
  - **Get anime Recyclerviews**
  - **Retrieve anime episode videos**
  - **Retrieve anime information**

### 3. User Interaction

Required Feature

- **User clicks on a show episode**
  - => **Show starts streaming**
-[x] **User clicks on show**
  - => **App displays list of episodes and description**
-[x] **User scrolls anime list**
    - => **App displays new anime shows**
- **User presses play/pause button in video**
    - => **App pauses/plays video**
-[x] **User presses back button**
    - => **App goes to the previous screen**
- **User presses full screen button**
    - => **App makes video full screen**
- 

## Wireframes
<!-- Add picture of your hand sketched wireframes in this section -->
![IMG_0740](https://hackmd.io/_uploads/SJIQIe5eC.jpg)
<img width="559" alt="wireframe" src="https://github.com/Anime-Studio/AS/assets/17733456/39f2a4d9-d901-477b-a0aa-76dfc57b68f5">


### [BONUS] Digital Wireframes & Mockups
![figma wireframe](https://github.com/Anime-Studio/AS/assets/123594031/09fb5cdf-2173-46fd-a216-a5f90d8591cc)

### [BONUS] Interactive Prototype
![Project8](https://github.com/Anime-Studio/AS/assets/123594031/c65a9ae7-a9a1-48ec-ac29-3ce71bffe7ce)


## Build Notes

Here's a place for any other notes on the app, it's creation 
process, or what you learned this unit!  

For Milestone 2, include **2+ Videos/GIFs** of the build process here!
![Project9](https://github.com/Anime-Studio/AS/assets/123594031/9d46b9c8-8286-4ac0-bac7-1508b08bfd16)

## License

Copyright **2024** 
**Michael Tanjuakio**
**Farhin Alavi Rashid**
**Manthra Natarajan**
**Eerav Kandikattu**
**Mymuna Murshed**
**Dhruv Tripathi**

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
