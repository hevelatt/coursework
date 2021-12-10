
namespace Elokuvasovellus
{
    partial class Paaikkuna
    {
        /// <summary>
        ///  Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        ///  Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        ///  Required method for Designer support - do not modify
        ///  the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.menuStripPaavalikko = new System.Windows.Forms.MenuStrip();
            this.tiedostoToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.poistuToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.tietojaToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.testaaTietokantayhteyttaToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.infoToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.panelPohja = new System.Windows.Forms.Panel();
            this.panelArvio = new System.Windows.Forms.Panel();
            this.groupBoxArvio = new System.Windows.Forms.GroupBox();
            this.richTextBoxArvio = new System.Windows.Forms.RichTextBox();
            this.tableLayoutPanelArvosana = new System.Windows.Forms.TableLayoutPanel();
            this.labelArvosana = new System.Windows.Forms.Label();
            this.numericUpDownArvosana1 = new System.Windows.Forms.NumericUpDown();
            this.labelArvosanaPiste = new System.Windows.Forms.Label();
            this.numericUpDownArvosana2 = new System.Windows.Forms.NumericUpDown();
            this.panelTiedot = new System.Windows.Forms.Panel();
            this.groupBoxTiedot = new System.Windows.Forms.GroupBox();
            this.tableLayoutPanelSelaus = new System.Windows.Forms.TableLayoutPanel();
            this.buttonEdellinen = new System.Windows.Forms.Button();
            this.buttonSeuraava = new System.Windows.Forms.Button();
            this.tableLayoutPanelTiedot = new System.Windows.Forms.TableLayoutPanel();
            this.labelId = new System.Windows.Forms.Label();
            this.labelNimi = new System.Windows.Forms.Label();
            this.labelVuosi = new System.Windows.Forms.Label();
            this.labelKesto = new System.Windows.Forms.Label();
            this.textBoxId = new System.Windows.Forms.TextBox();
            this.textBoxNimi = new System.Windows.Forms.TextBox();
            this.textBoxVuosi = new System.Windows.Forms.TextBox();
            this.textBoxKesto = new System.Windows.Forms.TextBox();
            this.panelToiminnot = new System.Windows.Forms.Panel();
            this.groupBoxToiminnot = new System.Windows.Forms.GroupBox();
            this.tableLayoutPanelToiminnot = new System.Windows.Forms.TableLayoutPanel();
            this.buttonUusi = new System.Windows.Forms.Button();
            this.buttonTallenna = new System.Windows.Forms.Button();
            this.buttonPoista = new System.Windows.Forms.Button();
            this.menuStripPaavalikko.SuspendLayout();
            this.panelPohja.SuspendLayout();
            this.panelArvio.SuspendLayout();
            this.groupBoxArvio.SuspendLayout();
            this.tableLayoutPanelArvosana.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.numericUpDownArvosana1)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.numericUpDownArvosana2)).BeginInit();
            this.panelTiedot.SuspendLayout();
            this.groupBoxTiedot.SuspendLayout();
            this.tableLayoutPanelSelaus.SuspendLayout();
            this.tableLayoutPanelTiedot.SuspendLayout();
            this.panelToiminnot.SuspendLayout();
            this.groupBoxToiminnot.SuspendLayout();
            this.tableLayoutPanelToiminnot.SuspendLayout();
            this.SuspendLayout();
            // 
            // menuStripPaavalikko
            // 
            this.menuStripPaavalikko.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.tiedostoToolStripMenuItem,
            this.tietojaToolStripMenuItem});
            this.menuStripPaavalikko.Location = new System.Drawing.Point(0, 0);
            this.menuStripPaavalikko.Name = "menuStripPaavalikko";
            this.menuStripPaavalikko.Size = new System.Drawing.Size(659, 24);
            this.menuStripPaavalikko.TabIndex = 0;
            this.menuStripPaavalikko.Text = "Päävalikko";
            // 
            // tiedostoToolStripMenuItem
            // 
            this.tiedostoToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.poistuToolStripMenuItem});
            this.tiedostoToolStripMenuItem.Name = "tiedostoToolStripMenuItem";
            this.tiedostoToolStripMenuItem.Size = new System.Drawing.Size(64, 20);
            this.tiedostoToolStripMenuItem.Text = "Tiedosto";
            // 
            // poistuToolStripMenuItem
            // 
            this.poistuToolStripMenuItem.Name = "poistuToolStripMenuItem";
            this.poistuToolStripMenuItem.Size = new System.Drawing.Size(107, 22);
            this.poistuToolStripMenuItem.Text = "Poistu";
            this.poistuToolStripMenuItem.Click += new System.EventHandler(this.poistuToolStripMenuItem_Click);
            // 
            // tietojaToolStripMenuItem
            // 
            this.tietojaToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.testaaTietokantayhteyttaToolStripMenuItem,
            this.infoToolStripMenuItem});
            this.tietojaToolStripMenuItem.Name = "tietojaToolStripMenuItem";
            this.tietojaToolStripMenuItem.Size = new System.Drawing.Size(54, 20);
            this.tietojaToolStripMenuItem.Text = "Tietoja";
            // 
            // testaaTietokantayhteyttaToolStripMenuItem
            // 
            this.testaaTietokantayhteyttaToolStripMenuItem.Name = "testaaTietokantayhteyttaToolStripMenuItem";
            this.testaaTietokantayhteyttaToolStripMenuItem.Size = new System.Drawing.Size(205, 22);
            this.testaaTietokantayhteyttaToolStripMenuItem.Text = "Testaa tietokantayhteyttä";
            // 
            // infoToolStripMenuItem
            // 
            this.infoToolStripMenuItem.Name = "infoToolStripMenuItem";
            this.infoToolStripMenuItem.Size = new System.Drawing.Size(205, 22);
            this.infoToolStripMenuItem.Text = "Info";
            this.infoToolStripMenuItem.Click += new System.EventHandler(this.infoToolStripMenuItem_Click);
            // 
            // panelPohja
            // 
            this.panelPohja.Controls.Add(this.panelArvio);
            this.panelPohja.Controls.Add(this.panelTiedot);
            this.panelPohja.Controls.Add(this.panelToiminnot);
            this.panelPohja.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panelPohja.Location = new System.Drawing.Point(0, 24);
            this.panelPohja.Margin = new System.Windows.Forms.Padding(0);
            this.panelPohja.Name = "panelPohja";
            this.panelPohja.Padding = new System.Windows.Forms.Padding(12, 6, 12, 12);
            this.panelPohja.Size = new System.Drawing.Size(659, 512);
            this.panelPohja.TabIndex = 1;
            // 
            // panelArvio
            // 
            this.panelArvio.Controls.Add(this.groupBoxArvio);
            this.panelArvio.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panelArvio.Location = new System.Drawing.Point(12, 296);
            this.panelArvio.Margin = new System.Windows.Forms.Padding(0);
            this.panelArvio.Name = "panelArvio";
            this.panelArvio.Size = new System.Drawing.Size(325, 204);
            this.panelArvio.TabIndex = 1;
            // 
            // groupBoxArvio
            // 
            this.groupBoxArvio.Controls.Add(this.richTextBoxArvio);
            this.groupBoxArvio.Controls.Add(this.tableLayoutPanelArvosana);
            this.groupBoxArvio.Dock = System.Windows.Forms.DockStyle.Fill;
            this.groupBoxArvio.Location = new System.Drawing.Point(0, 0);
            this.groupBoxArvio.Name = "groupBoxArvio";
            this.groupBoxArvio.Padding = new System.Windows.Forms.Padding(18, 9, 18, 9);
            this.groupBoxArvio.Size = new System.Drawing.Size(325, 204);
            this.groupBoxArvio.TabIndex = 0;
            this.groupBoxArvio.TabStop = false;
            this.groupBoxArvio.Text = "Arvio";
            // 
            // richTextBoxArvio
            // 
            this.richTextBoxArvio.Dock = System.Windows.Forms.DockStyle.Fill;
            this.richTextBoxArvio.Location = new System.Drawing.Point(18, 25);
            this.richTextBoxArvio.Name = "richTextBoxArvio";
            this.richTextBoxArvio.Size = new System.Drawing.Size(289, 117);
            this.richTextBoxArvio.TabIndex = 0;
            this.richTextBoxArvio.Text = "";
            this.richTextBoxArvio.Enter += new System.EventHandler(this.tekstilaatikko_Enter);
            this.richTextBoxArvio.Leave += new System.EventHandler(this.tekstilaatikko_Leave);
            // 
            // tableLayoutPanelArvosana
            // 
            this.tableLayoutPanelArvosana.AutoSize = true;
            this.tableLayoutPanelArvosana.ColumnCount = 4;
            this.tableLayoutPanelArvosana.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle());
            this.tableLayoutPanelArvosana.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle());
            this.tableLayoutPanelArvosana.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle());
            this.tableLayoutPanelArvosana.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle());
            this.tableLayoutPanelArvosana.Controls.Add(this.labelArvosana, 0, 0);
            this.tableLayoutPanelArvosana.Controls.Add(this.numericUpDownArvosana1, 1, 0);
            this.tableLayoutPanelArvosana.Controls.Add(this.labelArvosanaPiste, 2, 0);
            this.tableLayoutPanelArvosana.Controls.Add(this.numericUpDownArvosana2, 3, 0);
            this.tableLayoutPanelArvosana.Dock = System.Windows.Forms.DockStyle.Bottom;
            this.tableLayoutPanelArvosana.GrowStyle = System.Windows.Forms.TableLayoutPanelGrowStyle.FixedSize;
            this.tableLayoutPanelArvosana.Location = new System.Drawing.Point(18, 142);
            this.tableLayoutPanelArvosana.Name = "tableLayoutPanelArvosana";
            this.tableLayoutPanelArvosana.Padding = new System.Windows.Forms.Padding(0, 12, 0, 12);
            this.tableLayoutPanelArvosana.RowCount = 1;
            this.tableLayoutPanelArvosana.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 100F));
            this.tableLayoutPanelArvosana.Size = new System.Drawing.Size(289, 53);
            this.tableLayoutPanelArvosana.TabIndex = 1;
            // 
            // labelArvosana
            // 
            this.labelArvosana.Anchor = System.Windows.Forms.AnchorStyles.Left;
            this.labelArvosana.AutoSize = true;
            this.labelArvosana.Location = new System.Drawing.Point(3, 19);
            this.labelArvosana.Name = "labelArvosana";
            this.labelArvosana.Size = new System.Drawing.Size(59, 15);
            this.labelArvosana.TabIndex = 0;
            this.labelArvosana.Text = "Arvosana:";
            // 
            // numericUpDownArvosana1
            // 
            this.numericUpDownArvosana1.Anchor = System.Windows.Forms.AnchorStyles.Left;
            this.numericUpDownArvosana1.Location = new System.Drawing.Point(68, 15);
            this.numericUpDownArvosana1.Name = "numericUpDownArvosana1";
            this.numericUpDownArvosana1.Size = new System.Drawing.Size(45, 23);
            this.numericUpDownArvosana1.TabIndex = 1;
            // 
            // labelArvosanaPiste
            // 
            this.labelArvosanaPiste.AutoSize = true;
            this.labelArvosanaPiste.Dock = System.Windows.Forms.DockStyle.Fill;
            this.labelArvosanaPiste.Location = new System.Drawing.Point(119, 12);
            this.labelArvosanaPiste.Name = "labelArvosanaPiste";
            this.labelArvosanaPiste.Size = new System.Drawing.Size(13, 29);
            this.labelArvosanaPiste.TabIndex = 2;
            this.labelArvosanaPiste.Text = ". ";
            this.labelArvosanaPiste.TextAlign = System.Drawing.ContentAlignment.BottomLeft;
            // 
            // numericUpDownArvosana2
            // 
            this.numericUpDownArvosana2.Anchor = System.Windows.Forms.AnchorStyles.Left;
            this.numericUpDownArvosana2.Location = new System.Drawing.Point(138, 15);
            this.numericUpDownArvosana2.Name = "numericUpDownArvosana2";
            this.numericUpDownArvosana2.Size = new System.Drawing.Size(45, 23);
            this.numericUpDownArvosana2.TabIndex = 1;
            // 
            // panelTiedot
            // 
            this.panelTiedot.Controls.Add(this.groupBoxTiedot);
            this.panelTiedot.Dock = System.Windows.Forms.DockStyle.Top;
            this.panelTiedot.Location = new System.Drawing.Point(12, 6);
            this.panelTiedot.Margin = new System.Windows.Forms.Padding(0);
            this.panelTiedot.Name = "panelTiedot";
            this.panelTiedot.Padding = new System.Windows.Forms.Padding(0, 0, 0, 6);
            this.panelTiedot.Size = new System.Drawing.Size(325, 290);
            this.panelTiedot.TabIndex = 0;
            // 
            // groupBoxTiedot
            // 
            this.groupBoxTiedot.Controls.Add(this.tableLayoutPanelSelaus);
            this.groupBoxTiedot.Controls.Add(this.tableLayoutPanelTiedot);
            this.groupBoxTiedot.Dock = System.Windows.Forms.DockStyle.Fill;
            this.groupBoxTiedot.Location = new System.Drawing.Point(0, 0);
            this.groupBoxTiedot.Name = "groupBoxTiedot";
            this.groupBoxTiedot.Padding = new System.Windows.Forms.Padding(24, 18, 24, 18);
            this.groupBoxTiedot.Size = new System.Drawing.Size(325, 284);
            this.groupBoxTiedot.TabIndex = 0;
            this.groupBoxTiedot.TabStop = false;
            this.groupBoxTiedot.Text = "Elokuvan tiedot";
            // 
            // tableLayoutPanelSelaus
            // 
            this.tableLayoutPanelSelaus.ColumnCount = 2;
            this.tableLayoutPanelSelaus.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanelSelaus.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanelSelaus.Controls.Add(this.buttonEdellinen, 0, 0);
            this.tableLayoutPanelSelaus.Controls.Add(this.buttonSeuraava, 1, 0);
            this.tableLayoutPanelSelaus.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tableLayoutPanelSelaus.GrowStyle = System.Windows.Forms.TableLayoutPanelGrowStyle.FixedSize;
            this.tableLayoutPanelSelaus.Location = new System.Drawing.Point(24, 203);
            this.tableLayoutPanelSelaus.Name = "tableLayoutPanelSelaus";
            this.tableLayoutPanelSelaus.RowCount = 1;
            this.tableLayoutPanelSelaus.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanelSelaus.Size = new System.Drawing.Size(277, 63);
            this.tableLayoutPanelSelaus.TabIndex = 1;
            // 
            // buttonEdellinen
            // 
            this.buttonEdellinen.Dock = System.Windows.Forms.DockStyle.Fill;
            this.buttonEdellinen.Location = new System.Drawing.Point(3, 12);
            this.buttonEdellinen.Margin = new System.Windows.Forms.Padding(3, 12, 12, 3);
            this.buttonEdellinen.Name = "buttonEdellinen";
            this.buttonEdellinen.Size = new System.Drawing.Size(123, 48);
            this.buttonEdellinen.TabIndex = 0;
            this.buttonEdellinen.Text = "Edellinen";
            this.buttonEdellinen.UseVisualStyleBackColor = true;
            // 
            // buttonSeuraava
            // 
            this.buttonSeuraava.Dock = System.Windows.Forms.DockStyle.Fill;
            this.buttonSeuraava.Location = new System.Drawing.Point(150, 12);
            this.buttonSeuraava.Margin = new System.Windows.Forms.Padding(12, 12, 3, 3);
            this.buttonSeuraava.Name = "buttonSeuraava";
            this.buttonSeuraava.Size = new System.Drawing.Size(124, 48);
            this.buttonSeuraava.TabIndex = 1;
            this.buttonSeuraava.Text = "Seuraava";
            this.buttonSeuraava.UseVisualStyleBackColor = true;
            // 
            // tableLayoutPanelTiedot
            // 
            this.tableLayoutPanelTiedot.ColumnCount = 2;
            this.tableLayoutPanelTiedot.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle());
            this.tableLayoutPanelTiedot.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 100F));
            this.tableLayoutPanelTiedot.Controls.Add(this.labelId, 0, 0);
            this.tableLayoutPanelTiedot.Controls.Add(this.labelNimi, 0, 1);
            this.tableLayoutPanelTiedot.Controls.Add(this.labelVuosi, 0, 2);
            this.tableLayoutPanelTiedot.Controls.Add(this.labelKesto, 0, 3);
            this.tableLayoutPanelTiedot.Controls.Add(this.textBoxId, 1, 0);
            this.tableLayoutPanelTiedot.Controls.Add(this.textBoxNimi, 1, 1);
            this.tableLayoutPanelTiedot.Controls.Add(this.textBoxVuosi, 1, 2);
            this.tableLayoutPanelTiedot.Controls.Add(this.textBoxKesto, 1, 3);
            this.tableLayoutPanelTiedot.Dock = System.Windows.Forms.DockStyle.Top;
            this.tableLayoutPanelTiedot.Location = new System.Drawing.Point(24, 34);
            this.tableLayoutPanelTiedot.Name = "tableLayoutPanelTiedot";
            this.tableLayoutPanelTiedot.RowCount = 4;
            this.tableLayoutPanelTiedot.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 25F));
            this.tableLayoutPanelTiedot.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 25F));
            this.tableLayoutPanelTiedot.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 25F));
            this.tableLayoutPanelTiedot.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 25F));
            this.tableLayoutPanelTiedot.Size = new System.Drawing.Size(277, 169);
            this.tableLayoutPanelTiedot.TabIndex = 0;
            // 
            // labelId
            // 
            this.labelId.Anchor = System.Windows.Forms.AnchorStyles.Left;
            this.labelId.AutoSize = true;
            this.labelId.Location = new System.Drawing.Point(3, 13);
            this.labelId.Name = "labelId";
            this.labelId.Size = new System.Drawing.Size(21, 15);
            this.labelId.TabIndex = 0;
            this.labelId.Text = "ID:";
            // 
            // labelNimi
            // 
            this.labelNimi.Anchor = System.Windows.Forms.AnchorStyles.Left;
            this.labelNimi.AutoSize = true;
            this.labelNimi.Location = new System.Drawing.Point(3, 55);
            this.labelNimi.Name = "labelNimi";
            this.labelNimi.Size = new System.Drawing.Size(36, 15);
            this.labelNimi.TabIndex = 1;
            this.labelNimi.Text = "Nimi:";
            // 
            // labelVuosi
            // 
            this.labelVuosi.Anchor = System.Windows.Forms.AnchorStyles.Left;
            this.labelVuosi.AutoSize = true;
            this.labelVuosi.Location = new System.Drawing.Point(3, 97);
            this.labelVuosi.Name = "labelVuosi";
            this.labelVuosi.Size = new System.Drawing.Size(79, 15);
            this.labelVuosi.TabIndex = 2;
            this.labelVuosi.Text = "Julkaisuvuosi:";
            // 
            // labelKesto
            // 
            this.labelKesto.Anchor = System.Windows.Forms.AnchorStyles.Left;
            this.labelKesto.AutoSize = true;
            this.labelKesto.Location = new System.Drawing.Point(3, 140);
            this.labelKesto.Name = "labelKesto";
            this.labelKesto.Size = new System.Drawing.Size(71, 15);
            this.labelKesto.TabIndex = 3;
            this.labelKesto.Text = "Kesto (min):";
            // 
            // textBoxId
            // 
            this.textBoxId.Anchor = System.Windows.Forms.AnchorStyles.Left;
            this.textBoxId.Location = new System.Drawing.Point(88, 9);
            this.textBoxId.Name = "textBoxId";
            this.textBoxId.ReadOnly = true;
            this.textBoxId.Size = new System.Drawing.Size(100, 23);
            this.textBoxId.TabIndex = 4;
            this.textBoxId.TabStop = false;
            // 
            // textBoxNimi
            // 
            this.textBoxNimi.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Left | System.Windows.Forms.AnchorStyles.Right)));
            this.textBoxNimi.Location = new System.Drawing.Point(88, 51);
            this.textBoxNimi.Name = "textBoxNimi";
            this.textBoxNimi.Size = new System.Drawing.Size(186, 23);
            this.textBoxNimi.TabIndex = 4;
            this.textBoxNimi.Enter += new System.EventHandler(this.tekstilaatikko_Enter);
            this.textBoxNimi.Leave += new System.EventHandler(this.tekstilaatikko_Leave);
            // 
            // textBoxVuosi
            // 
            this.textBoxVuosi.Anchor = System.Windows.Forms.AnchorStyles.Left;
            this.textBoxVuosi.Location = new System.Drawing.Point(88, 93);
            this.textBoxVuosi.Name = "textBoxVuosi";
            this.textBoxVuosi.Size = new System.Drawing.Size(100, 23);
            this.textBoxVuosi.TabIndex = 4;
            this.textBoxVuosi.Enter += new System.EventHandler(this.tekstilaatikko_Enter);
            this.textBoxVuosi.Leave += new System.EventHandler(this.tekstilaatikko_Leave);
            // 
            // textBoxKesto
            // 
            this.textBoxKesto.Anchor = System.Windows.Forms.AnchorStyles.Left;
            this.textBoxKesto.Location = new System.Drawing.Point(88, 136);
            this.textBoxKesto.Name = "textBoxKesto";
            this.textBoxKesto.Size = new System.Drawing.Size(100, 23);
            this.textBoxKesto.TabIndex = 4;
            this.textBoxKesto.Enter += new System.EventHandler(this.tekstilaatikko_Enter);
            this.textBoxKesto.Leave += new System.EventHandler(this.tekstilaatikko_Leave);
            // 
            // panelToiminnot
            // 
            this.panelToiminnot.Controls.Add(this.groupBoxToiminnot);
            this.panelToiminnot.Dock = System.Windows.Forms.DockStyle.Right;
            this.panelToiminnot.Location = new System.Drawing.Point(337, 6);
            this.panelToiminnot.Margin = new System.Windows.Forms.Padding(0);
            this.panelToiminnot.Name = "panelToiminnot";
            this.panelToiminnot.Padding = new System.Windows.Forms.Padding(9, 0, 0, 0);
            this.panelToiminnot.Size = new System.Drawing.Size(310, 494);
            this.panelToiminnot.TabIndex = 2;
            // 
            // groupBoxToiminnot
            // 
            this.groupBoxToiminnot.Controls.Add(this.tableLayoutPanelToiminnot);
            this.groupBoxToiminnot.Dock = System.Windows.Forms.DockStyle.Fill;
            this.groupBoxToiminnot.Location = new System.Drawing.Point(9, 0);
            this.groupBoxToiminnot.Name = "groupBoxToiminnot";
            this.groupBoxToiminnot.Padding = new System.Windows.Forms.Padding(24);
            this.groupBoxToiminnot.Size = new System.Drawing.Size(301, 494);
            this.groupBoxToiminnot.TabIndex = 0;
            this.groupBoxToiminnot.TabStop = false;
            this.groupBoxToiminnot.Text = "Toiminnallisuus";
            // 
            // tableLayoutPanelToiminnot
            // 
            this.tableLayoutPanelToiminnot.ColumnCount = 1;
            this.tableLayoutPanelToiminnot.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 100F));
            this.tableLayoutPanelToiminnot.Controls.Add(this.buttonUusi, 0, 0);
            this.tableLayoutPanelToiminnot.Controls.Add(this.buttonTallenna, 0, 1);
            this.tableLayoutPanelToiminnot.Controls.Add(this.buttonPoista, 0, 2);
            this.tableLayoutPanelToiminnot.Dock = System.Windows.Forms.DockStyle.Top;
            this.tableLayoutPanelToiminnot.Location = new System.Drawing.Point(24, 40);
            this.tableLayoutPanelToiminnot.Name = "tableLayoutPanelToiminnot";
            this.tableLayoutPanelToiminnot.RowCount = 3;
            this.tableLayoutPanelToiminnot.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 33.33333F));
            this.tableLayoutPanelToiminnot.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 33.33333F));
            this.tableLayoutPanelToiminnot.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 33.33333F));
            this.tableLayoutPanelToiminnot.Size = new System.Drawing.Size(253, 200);
            this.tableLayoutPanelToiminnot.TabIndex = 0;
            // 
            // buttonUusi
            // 
            this.buttonUusi.Dock = System.Windows.Forms.DockStyle.Fill;
            this.buttonUusi.Location = new System.Drawing.Point(0, 6);
            this.buttonUusi.Margin = new System.Windows.Forms.Padding(0, 6, 0, 6);
            this.buttonUusi.Name = "buttonUusi";
            this.buttonUusi.Size = new System.Drawing.Size(253, 54);
            this.buttonUusi.TabIndex = 0;
            this.buttonUusi.Text = "Uusi tietue";
            this.buttonUusi.UseVisualStyleBackColor = true;
            this.buttonUusi.Click += new System.EventHandler(this.buttonUusi_Click);
            // 
            // buttonTallenna
            // 
            this.buttonTallenna.BackColor = System.Drawing.Color.LawnGreen;
            this.buttonTallenna.Dock = System.Windows.Forms.DockStyle.Fill;
            this.buttonTallenna.Location = new System.Drawing.Point(0, 72);
            this.buttonTallenna.Margin = new System.Windows.Forms.Padding(0, 6, 0, 6);
            this.buttonTallenna.Name = "buttonTallenna";
            this.buttonTallenna.Size = new System.Drawing.Size(253, 54);
            this.buttonTallenna.TabIndex = 1;
            this.buttonTallenna.Text = "Tallenna";
            this.buttonTallenna.UseVisualStyleBackColor = false;
            this.buttonTallenna.Click += new System.EventHandler(this.buttonTallenna_Click);
            // 
            // buttonPoista
            // 
            this.buttonPoista.BackColor = System.Drawing.Color.Brown;
            this.buttonPoista.Dock = System.Windows.Forms.DockStyle.Fill;
            this.buttonPoista.ForeColor = System.Drawing.SystemColors.Control;
            this.buttonPoista.Location = new System.Drawing.Point(0, 138);
            this.buttonPoista.Margin = new System.Windows.Forms.Padding(0, 6, 0, 6);
            this.buttonPoista.Name = "buttonPoista";
            this.buttonPoista.Size = new System.Drawing.Size(253, 56);
            this.buttonPoista.TabIndex = 2;
            this.buttonPoista.Text = "Poista";
            this.buttonPoista.UseVisualStyleBackColor = false;
            // 
            // Paaikkuna
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 15F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.AutoScroll = true;
            this.AutoScrollMinSize = new System.Drawing.Size(555, 455);
            this.ClientSize = new System.Drawing.Size(659, 536);
            this.Controls.Add(this.panelPohja);
            this.Controls.Add(this.menuStripPaavalikko);
            this.MainMenuStrip = this.menuStripPaavalikko;
            this.Name = "Paaikkuna";
            this.Text = "Elokuvasovellus";
            this.menuStripPaavalikko.ResumeLayout(false);
            this.menuStripPaavalikko.PerformLayout();
            this.panelPohja.ResumeLayout(false);
            this.panelArvio.ResumeLayout(false);
            this.groupBoxArvio.ResumeLayout(false);
            this.groupBoxArvio.PerformLayout();
            this.tableLayoutPanelArvosana.ResumeLayout(false);
            this.tableLayoutPanelArvosana.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.numericUpDownArvosana1)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.numericUpDownArvosana2)).EndInit();
            this.panelTiedot.ResumeLayout(false);
            this.groupBoxTiedot.ResumeLayout(false);
            this.tableLayoutPanelSelaus.ResumeLayout(false);
            this.tableLayoutPanelTiedot.ResumeLayout(false);
            this.tableLayoutPanelTiedot.PerformLayout();
            this.panelToiminnot.ResumeLayout(false);
            this.groupBoxToiminnot.ResumeLayout(false);
            this.tableLayoutPanelToiminnot.ResumeLayout(false);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.MenuStrip menuStripPaavalikko;
        private System.Windows.Forms.ToolStripMenuItem tiedostoToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem poistuToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem tietojaToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem testaaTietokantayhteyttaToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem infoToolStripMenuItem;
        private System.Windows.Forms.Panel panelPohja;
        private System.Windows.Forms.Panel panelArvio;
        private System.Windows.Forms.Panel panelTiedot;
        private System.Windows.Forms.Panel panelToiminnot;
        private System.Windows.Forms.GroupBox groupBoxArvio;
        private System.Windows.Forms.GroupBox groupBoxTiedot;
        private System.Windows.Forms.GroupBox groupBoxToiminnot;
        private System.Windows.Forms.TableLayoutPanel tableLayoutPanelTiedot;
        private System.Windows.Forms.Label labelId;
        private System.Windows.Forms.Label labelNimi;
        private System.Windows.Forms.Label labelVuosi;
        private System.Windows.Forms.Label labelKesto;
        private System.Windows.Forms.TextBox textBoxId;
        private System.Windows.Forms.TextBox textBoxNimi;
        private System.Windows.Forms.TextBox textBoxVuosi;
        private System.Windows.Forms.TextBox textBoxKesto;
        private System.Windows.Forms.TableLayoutPanel tableLayoutPanelSelaus;
        private System.Windows.Forms.Button buttonEdellinen;
        private System.Windows.Forms.Button buttonSeuraava;
        private System.Windows.Forms.TableLayoutPanel tableLayoutPanelToiminnot;
        private System.Windows.Forms.Button buttonUusi;
        private System.Windows.Forms.Button buttonTallenna;
        private System.Windows.Forms.Button buttonPoista;
        private System.Windows.Forms.RichTextBox richTextBoxArvio;
        private System.Windows.Forms.TableLayoutPanel tableLayoutPanelArvosana;
        private System.Windows.Forms.Label labelArvosana;
        private System.Windows.Forms.NumericUpDown numericUpDownArvosana1;
        private System.Windows.Forms.Label labelArvosanaPiste;
        private System.Windows.Forms.NumericUpDown numericUpDownArvosana2;
    }
}

