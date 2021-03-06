# Surfeit
A food-waste reduction app that assists users in tracking
expiration dates and using leftovers.

## Group Information
### Members
- Olivia Coleman
- Sam Dobesh
- Noah Hance
- Luther Nguyen

### Class
CSCI 536: Tech for Social Good.

## Quick Start
1. Download android studio https://developer.android.com/studio
2. Clone the project with `git clone https://github.com/sjdobesh/surfeit.git`
3. Open the Project with android studio `file>open>`
4. Add an emulator `tools>avd manager>`
  - Click `+ add virtual device` and choose any, we are using pixel 3
5. Build with green hammer
6. Run with green arrow

## Milestones
1. Nov 1st -- Template and navigation set up
  - [X] Set up git
    - [X] Set up .gitignore 
    - [X] Set up branch protection
    - [X] Add team members
      - [X] Add Olivia
      - [X] Add Sam
      - [X] Add Noah
      - [X] Add Luther
  - [X] Push starting template
  - [X] Determine features scope
  - [X] Set milestones & division of labor
  - [X] Fragments set up
  - [X] Fragment transitions / page navigation
  - [X] Basic UI components, can be static place holders for now

#### Division of labor:
- [X] Olivia
  - [X] Category page
- [X] Sam
  - [X] Git junk
  - [X] Paging and navigation controls
- [X] Noah
  - [X] Home page
- [X] Luther
  - [X] Camera page

2. Nov 12th -- Database set up
  - [X] SQLite
  - [X] Read and write entries
  - [ ] Display database entries in the app
  - [X] Dynamic UI components
    - [X] Visual representation of database entries
    - [X] Camera interface

  ### Division of labor:
  - [ ] Olivia
  - [X] Sam
    - [X] Git management
    - [X] Set up database manager
      - [X] Make Food class for db entries
      - [X] Write
      - [X] Read
  - [X] Noah
    - [X] Home Page UI components
      - [X] Allow for updating savings, etc.
      - [X] Count days since last scan
  - [X] Luther
    - [X] Camera interface
        - [X] Scans barcode
        - [X] Displays decoded barcode

### 3. Nov 23rd -- Scanning
- [X] Camera scanning implemented
- [X] Manual entry on failed scans available
- [X] User database management should be fully functional
  - [ ] Categories
    - [ ] Basic default categories for common items
    - [ ] Leftovers
  - [ ] Be able to edit existing items
  - [ ] Reorganizing

#### Division of labor:
- [X] Olivia
  - [X] Icon design and implementation
  - [X] Logo design and implementation
- [X] Sam
  - [X] ListView for fridge page
  - [X] Populate ListView from database
  - [X] Git Management
- [ ] Noah
  - [ ] Scanned Product Page
    - [ ] Display info on scanned item
    - [ ] Uses upcdatabase.com API
- [X] Luther
  - [X] Manual entry of products
    - [X] Product is written/saved to database

### 4. Dec 9th -- Polishing
- [X] Icons
- [X] Color Scheme
- [ ] Animations
- [X] Manual Testing
- [X] Address any problems from earlier milestones

#### Division of labor:
- [X] Olivia
  - [X] Updated display of database in fridge tab
- [X] Sam
  - [X] Git management/code review
- [X] Noah
  - [X] Display product info or reason for invalid scan
  - [X] Transfer scanned item data to populate the manual input page
- [X] Luther
  - [X] Remove product from fridge and update contents/view
