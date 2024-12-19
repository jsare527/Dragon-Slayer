# DragonSlayer Pull List Program
Capstone Project UNO 2023

# How to use

The DragonSlayer program is used to maintain a comic book store Pull List. Customer's can be added through the customer tab, titles throught the title tab, and reports and data analytics can be viewed through the reports tab.

# Branches

**main branch:** 
- This is the branch to hold live data and without bugs and will be the branch used for releases.
  
**dev branch:**
- This is the branch used for development and holds the current updates to be tested by the group.

**mainV2 branch:**
- This is the branch which holds the DragonSlayerV2 code.

# Release Notes - Dragon Slayer V4

**RELEASE NOTES 3.01**

*Code Milestone 1 (10.17.2024)*
  - Repository set up
  - plans to fix following bugs:
      - removing flags not working
      - fix search button
      - add type to search feature within requested user items**

*Code Milestone 2 (11.02.2024)*
  - (10.31.2024) commits made to add new function for searching titles
      - fixes the scrollbar bug that occured when searching

*Code Milestone 3 (11.27.2024)*
  - added red highlights to determine when a title has not been flagged after 6 months
  - Testing added
    
*Code Milestone 4 (12.07.2024)*
  - further highlights added
  - new button for title interface
  - adjusted to be deployable

*Code Milestone 4 (12.07.2024)*
  - App deployed into executable form for use

# Release Notes - Dragon Slayer V3

**RELEASE NOTES 2.01**

*Code Milestone 1 (9.28.2023)*
  - Search: Fix inconsistent customer lists
  - Added Cucumber testing framework

*Code Milestone 2 (10.19.23)*
  - Performance: Improve time it takes to save flags or perform updates

*Code Milestone 3 (11.2.2023)*
  - Search: Remove case sensitive issues when searching or sorting by title
  - Details: Formatting problem with title overlapping with last flagged date with long titles
  - Search: Be able to search by product id within titles field
  - Save Flags: Flags automatically save and remain saved

*Code Milestone 4 (11.15.2023)*
  - Search: Highlight titles which have 0 associated customers
  - Search: New search with each input

*Code Milestone 5 (12.8.2023)*
  - Reports: Update report for items which have not been flagged in 6 months but were created in those 6 months
  - Search: Be able to scroll down to an item by highlighting a title and starting to type
  - Reports: Remember last saved location for reports
  - Backup: Create a backup option

# Release Notes - Dragon Slayer V2

**RELEASE NOTES 1.01d (12.10.2022)**

-Added ability to mark and track delinquent customers.

-If a title was made in the last sixth months, it no longer triggers the "title not flagged for 6 months" warning.

-SQL calls to the database have been optimized and data is now cached. Backing out of menus no longer triggers calls. Searching is faster.

-Settings file made that tracks the installation location of the database and allows for it to be moved manually by the user.

-Multiple titles can be selected and flagged at once now using the CTRL+M shortcut.

**RELEASE NOTES 1.01c (11.17.2022)**

-Multiple customers, titles, and orders are now selectable at a time. This allows for mass deletion or comparison.

-Exports and the list of titles not flagged in the last six months was made more clearly visible and readable.

-Enforcing flag saving before editing titles now works correctly.

-The front-end for showing delinquent customers has been made.

-Program now checks and updates an old database's schema to be compatible with the new schema.

-Date created is now being tracked and displayed for titles. If no date is available "unknown" is displayed.

-Product ID is now tracked and displayed for titles.

-Notes are now tracked and displayed for customers.

**RELEASE NOTES 1.01b (11.03.2022)**

-Monthly logs are now created and tracked under the "/logs" folder.

-Clicking a title in the title screen now shows a live display of which customers are requesting that title.

-Titles now sort properly, ignoring case differences. Price sorting also fixed. 

-Fixed Customers able to be added with blank information.

-Warning is now displayed when deleting a title with customer requests.

-Deleting a customer's request now shows the correct title name.

-Duplicate requests for a customer are no longer allowed.

**RELEASE NOTES 1.01a (10.20.2022)**

-Search function now in working order. Search customer's by first or last name and title by name.

-Client can now flag a title using the hotkey **Ctrl+M**.

-Several bugs related to adding a new order fixed.

**RELEASE NOTES 1.01 (10.06.2022)**

-UI overhaul completed

-Dummy search added (UI working, backend not implmeneted)

-Prepopulated quantity field (one of the biggest client requests) now implmented.

-Several bugs related to editing the customers fixed.

-Improved flagging alerts.
